package morningsage.particletitlescreen;

import com.sun.javafx.geom.Vec2d;
import lombok.var;
import morningsage.particletitlescreen.config.ModConfig;
import morningsage.particletitlescreen.events.RotatingCubeMapEvents;
import morningsage.particletitlescreen.utils.RandomUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.ActionResult;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class ParticleScreenManager {
    private final List<Particle> particles = new ArrayList<>();

    public ParticleScreenManager() {
        initParticles();

        RotatingCubeMapEvents.ON_RENDER.register(this::onRender);
    }

    private void initParticles() {
        particles.clear();

        for (int i = 0; i < ModConfig.maxParticles; i++) {
            var particleBuilder = Particle.builder();

            if (ModConfig.randomParticleRadius) {
                particleBuilder.radius(RandomUtils.getRandomFloat(ModConfig.particleMinRadius, ModConfig.particleMaxRadius));
            } else {
                particleBuilder.radius(ModConfig.particleRadius);
            }

            if (ModConfig.randomParticleOpacity) {
                particleBuilder.opacity(RandomUtils.getRandomFloat(ModConfig.particleMinOpacity, ModConfig.particleMaxOpacity));
            } else {
                particleBuilder.opacity(ModConfig.particleOpacity);
            }

            if (ModConfig.particleMovement) {
                particleBuilder.speed(ModConfig.particleMovementSpeed);
            }

            particleBuilder.color(Integer.decode(ModConfig.particleColor));
            particleBuilder.locationVec(RandomUtils.getRandomLocation());
            particleBuilder.baseVelocity(new Vec2d());

            particles.add(particleBuilder.build());
        }
    }

    private ActionResult onRender() {
        onRenderBackground();
        onRenderParticles();

        return ActionResult.CONSUME;
    }
    private static void onRenderBackground() {
        int background = Integer.decode(ModConfig.backgroundColor);

        final float red   = (float)(background >> 16 & 255) / 255.0F;
        final float green = (float)(background >>  8 & 255) / 255.0F;
        final float blue  = (float)(background       & 255) / 255.0F;

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
        // Move first to make sure all the interactions match up
        if (ModConfig.particleMovement) {
            for (Particle particle : particles) {
                particle.move();
                RandomUtils.moveParticleIfNeeded(particle, ModConfig.particleBounce);
            }
        }

        // Then draw everything
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).draw();

            if (ModConfig.particleInteractions) {
                for (int i1 = i + 1; i1 < particles.size(); i1++) {
                    particles.get(i).interact(particles.get(i1), ModConfig.particleInteractionDistance, ModConfig.particleInteractionOpacity, Integer.decode(ModConfig.particleInteractionColor));
                }
            }
        }
    }

}
