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
        // Move if the particle is outside the window
        if (particle.getLocationVec().x - particle.getRadius() > minecraftWindow.getScaledWidth()) {
            particle.getLocationVec().set(-particle.getRadius(), getRandomY());
        } else if (particle.getLocationVec().x + particle.getRadius() < 0) {
            particle.getLocationVec().set(minecraftWindow.getScaledWidth() + particle.getRadius(), getRandomY());
        }

        if (particle.getLocationVec().y - particle.getRadius() > minecraftWindow.getScaledHeight()) {
            particle.getLocationVec().set(getRandomX(), -particle.getRadius());
        } else if (particle.getLocationVec().y + particle.getRadius() < 0) {
            particle.getLocationVec().set(getRandomX(), minecraftWindow.getScaledHeight() + particle.getRadius());
        }

        // Bounce nicely off the sides if enabled
        if (bounce) {
            if (particle.getLocationVec().x + particle.getRadius() > minecraftWindow.getScaledWidth() || particle.getLocationVec().x - particle.getRadius() < 0) {
                particle.getRealizedVelocity().x = -particle.getRealizedVelocity().x;
            }

            if (particle.getLocationVec().y + particle.getRadius() > minecraftWindow.getScaledHeight() || particle.getLocationVec().y - particle.getRadius() < 0) {
                particle.getRealizedVelocity().y = -particle.getRealizedVelocity().y;
            }
        }
    }
}