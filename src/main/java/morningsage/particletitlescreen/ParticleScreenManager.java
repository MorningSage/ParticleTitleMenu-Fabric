package morningsage.particletitlescreen;

import com.sun.javafx.geom.Vec2d;
import lombok.var;
import morningsage.particletitlescreen.config.ModConfig;
import morningsage.particletitlescreen.events.MouseEvents;
import morningsage.particletitlescreen.events.ResolutionChangedEvent;
import morningsage.particletitlescreen.events.RotatingCubeMapEvents;
import morningsage.particletitlescreen.utils.RandomUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import net.minecraft.util.ActionResult;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

import static morningsage.particletitlescreen.config.ModConfig.*;

public class ParticleScreenManager {
    private final List<Particle> particles = new ArrayList<>();
    private static final Vec2d ZERO = new Vec2d();
    private @Nullable Vec2d mouseLocation = null;
    private @Nullable Vec2d resolution = null;
    private @Nullable Double scale = null;
    private static final Window window = MinecraftClient.getInstance().getWindow();

    public ParticleScreenManager() {
        initParticles();
        initMouseEvents();

        RotatingCubeMapEvents.ON_RENDER.register(this::onRender);
        ResolutionChangedEvent.ON_CHANGED.register(this::initParticles);
    }

    private void initParticles() {
        particles.clear();

        int particleColor = Integer.decode(ModConfig.particleColor);
        double particleCount = window.getFramebufferWidth() * window.getFramebufferHeight() / window.getScaleFactor() / 4000;

        for (int i = 0; i < particleCount; i++) {
            var particleBuilder = Particle.builder();

            if (randomParticleRadius) {
                particleBuilder.radius(RandomUtils.getRandomFloat(particleMinRadius, particleMaxRadius));
            } else {
                particleBuilder.radius(particleRadius);
            }

            if (randomParticleOpacity) {
                particleBuilder.opacity(RandomUtils.getRandomFloat(particleMinOpacity, particleMaxOpacity));
            } else {
                particleBuilder.opacity(particleOpacity);
            }

            if (particleMovement) {
                particleBuilder.speed(particleMovementSpeed);
            }

            particleBuilder.color(particleColor);
            particleBuilder.locationVec(RandomUtils.getRandomLocation());
            particleBuilder.baseVelocity(ZERO);

            particles.add(particleBuilder.build());
        }
    }
    private void initMouseEvents() {
        if (!particleRepelledByMouse) return;

        MouseEvents.ON_LEAVE.register(() -> mouseLocation = null);
        MouseEvents.ON_MOVE.register((window, x, y) -> {
            if (mouseLocation == null) {
                mouseLocation = new Vec2d(x, y);
            } else {
                mouseLocation.set(x, y);
            }
        });
    }
    private ActionResult onRender() {
        onRenderBackground();
        onRenderParticles();

        if (resolution == null || resolution.x != window.getScaledWidth() || resolution.y != window.getScaledHeight()) {
            resolution = new Vec2d(window.getScaledWidth(), window.getScaledHeight());
        }

        if (scale == null || scale != window.getScaleFactor()) {
            scale = window.getScaleFactor();
        }

        return ActionResult.CONSUME;
    }
    private static void onRenderBackground() {
        int backgroundColor = Integer.decode(ModConfig.backgroundColor);

        final float red   = (float)(backgroundColor >> 16 & 255) / 255.0F;
        final float green = (float)(backgroundColor >>  8 & 255) / 255.0F;
        final float blue  = (float)(backgroundColor       & 255) / 255.0F;

        int width  = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int height = MinecraftClient.getInstance().getWindow().getScaledHeight();

        GL11.glColor3f(red, green, blue);

        GL11.glBegin(GL11.GL_QUAD_STRIP);
        GL11.glVertex2i(0, 0);
        GL11.glVertex2i(0, height);
        GL11.glVertex2i(width, 0);
        GL11.glVertex2i(width, height);
        GL11.glEnd();
    }
    private void onRenderParticles() {
        // Determine position first to make sure all the interactions match up
        if (particleMovement) {
            Vec2d windowSize = new Vec2d(window.getScaledWidth(), window.getScaledHeight());

            for (Particle particle : particles) {
                particle.move();
                RandomUtils.moveParticleIfNeeded(particle, particleBounce);
                particle.interactWithMouse(mouseLocation, windowSize, particleBounce, particleDistanceRepelledByMouse, window.getScaleFactor());
            }
        }

        int particleInteractionColor = particleInteractions ? Integer.decode(ModConfig.particleInteractionColor) : -1;

        // Then draw everything
        for (int i = 0; i < particles.size(); i++) {
            Particle particle1 = particles.get(i);

            particle1.draw();

            if (particleInteractions) {
                for (int x = i + 1; x < particles.size(); x++) {
                    particle1.interact(
                        particles.get(x),
                        particleInteractionDistance,
                        particleInteractionOpacity,
                        particleInteractionColor
                    );
                }
            }
        }
    }
}
