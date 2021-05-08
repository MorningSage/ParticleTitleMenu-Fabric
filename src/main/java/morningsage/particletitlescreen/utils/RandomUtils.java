package morningsage.particletitlescreen.utils;

import com.sun.javafx.geom.Vec2d;
import morningsage.particletitlescreen.Particle;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;

import java.util.Random;

public final class RandomUtils {
    private static final Random random = new Random();
    private static final Window minecraftWindow = MinecraftClient.getInstance().getWindow();

    public static float getRandomFloat(float min, float max) {
        return min + random.nextFloat() * (max - min);
    }
    public static float getRandomFloat() {
        return getRandomFloat(0, 1);
    }

    private static float getRandomX() {
        return getRandomFloat(0, minecraftWindow.getScaledWidth());
    }

    private static float getRandomY() {
        return getRandomFloat(0, minecraftWindow.getScaledHeight());
    }

    public static Vec2d getRandomLocation() {
        return new Vec2d(getRandomX(), getRandomY());
    }

    public static void moveParticleIfNeeded(Particle particle, boolean bounce) {
        int width  = minecraftWindow.getScaledWidth();
        int height = minecraftWindow.getScaledHeight();

        Vec2d location = particle.getLocationVec();
        float radius   = particle.getRadius();

        if (bounce) {
            Vec2d velocity = particle.getRealizedVelocity();

            if (location.x + radius > width || location.x - radius < 0) {
                velocity.x = -velocity.x;
            }

            if (location.y + radius > height || location.y - radius < 0) {
                velocity.y = -velocity.y;
            }
        } else {
            if (location.x - radius > width) {
                location.set(-radius, getRandomY());
            } else if (location.x + radius < 0) {
                location.set(width + radius, getRandomY());
            }

            if (location.y - radius > height) {
                location.set(getRandomX(), -radius);
            } else if (location.y + radius < 0) {
                location.set(getRandomX(), height + radius);
            }
        }
    }
}