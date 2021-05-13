package morningsage.particletitlescreen.utils;

import morningsage.particletitlescreen.Particle;
import morningsage.particletitlescreen.ducks.Vec2fDuck;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import net.minecraft.util.math.Vec2f;

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

    public static Vec2f getRandomLocation() {
        return new Vec2f(getRandomX(), getRandomY());
    }

    public static void moveParticleIfNeeded(Particle particle, boolean bounce) {
        int width  = minecraftWindow.getScaledWidth();
        int height = minecraftWindow.getScaledHeight();

        Vec2fDuck location = (Vec2fDuck) particle.getLocationVec();
        float radius   = particle.getRadius();

        if (bounce) {
            Vec2fDuck velocity = (Vec2fDuck) particle.getRealizedVelocity();

            if (location.getX() + radius > width || location.getX() - radius < 0) {
                velocity.setX(-velocity.getX());
            }

            if (location.getY() + radius > height || location.getY() - radius < 0) {
                velocity.setY(-velocity.getY());
            }
        } else {
            if (location.getX() - radius > width) {
                location.set(-radius, getRandomY());
            } else if (location.getX() + radius < 0) {
                location.set(width + radius, getRandomY());
            }

            if (location.getY() - radius > height) {
                location.set(getRandomX(), -radius);
            } else if (location.getY() + radius < 0) {
                location.set(getRandomX(), height + radius);
            }
        }
    }
}