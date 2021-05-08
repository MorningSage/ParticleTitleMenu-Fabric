package morningsage.particletitlescreen;

import com.sun.javafx.geom.Vec2d;
import lombok.*;
import morningsage.particletitlescreen.utils.RandomUtils;
import morningsage.particletitlescreen.utils.RenderUtils;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

@Data @Builder
public class Particle {

    @NonNull Vec2d locationVec;
    @Builder.Default float opacity = 1.0F;
    @Builder.Default int color = 0xFFFFFFFF;
    @Builder.Default float radius = 5;
    @Builder.Default Vec2d baseVelocity = new Vec2d();
    @Builder.Default double speed = 0;

    Vec2d realizedVelocity;

    public void interact(Particle particle, double maximumDistance, float maxOpacity, int color) {
        double dist = locationVec.distance(particle.getLocationVec());

        if (dist > maximumDistance) return;

        RenderUtils.renderLine(
            locationVec, particle.locationVec,
            1.0F, color,
            (float)(maxOpacity - dist / (1 / maxOpacity) / maximumDistance)
        );
    }

    public void draw() {
        RenderUtils.renderPoint(locationVec, radius, color, opacity);
    }

    public void move() {
        if (realizedVelocity == null) {
            realizedVelocity = new Vec2d(
                baseVelocity.x + RandomUtils.getRandomFloat() - 0.5,
                baseVelocity.y + RandomUtils.getRandomFloat() - 0.5
            );
        }

        double ms = speed / 2;

        locationVec.set(locationVec.x + realizedVelocity.x * ms, locationVec.y + realizedVelocity.y * ms);
    }
    public void interactWithMouse(@Nullable Vec2d mouse, Vec2d windowSize, boolean bounce, double repelledRadius, double scale) {
        if (mouse == null) return;

        double scaledRepelledRadius = -10 * scale + repelledRadius;

        Vec2d tmp = new Vec2d(locationVec.x - mouse.x / scale, locationVec.y - mouse.y / scale);
        double dist_mouse = Math.sqrt(tmp.x * tmp.x + tmp.y * tmp.y);

        Vec2d normVec = new Vec2d(tmp.x / dist_mouse, tmp.y / dist_mouse);
        double repelFactor = MathHelper.clamp((1 / scaledRepelledRadius) * (-1 * Math.pow(dist_mouse / scaledRepelledRadius, 2) + 1) * scaledRepelledRadius * 100, 0, 50);

        Vec2d newPosition = new Vec2d(
            locationVec.x + normVec.x * repelFactor,
            locationVec.y + normVec.y * repelFactor
        );

        if (bounce) {
            if (newPosition.x - radius > 0 && newPosition.x + radius < windowSize.x) locationVec.x = newPosition.x;
            if (newPosition.y - radius > 0 && newPosition.y + radius < windowSize.y) locationVec.y = newPosition.y;
        } else {
            locationVec.x = newPosition.x;
            locationVec.y = newPosition.y;
        }
    }
}


