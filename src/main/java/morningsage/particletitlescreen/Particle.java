package morningsage.particletitlescreen;

import lombok.*;
import morningsage.particletitlescreen.ducks.Vec2fDuck;
import morningsage.particletitlescreen.utils.RandomUtils;
import morningsage.particletitlescreen.utils.RenderUtils;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import org.jetbrains.annotations.Nullable;

@Data @Builder
public class Particle {

    @NonNull Vec2f locationVec;
    @Builder.Default float opacity = 1.0F;
    @Builder.Default int color = 0xFFFFFFFF;
    @Builder.Default float radius = 5.0F;
    @Builder.Default Vec2f baseVelocity = Vec2f.ZERO;
    @Builder.Default float speed = 0.0F;

    Vec2f realizedVelocity;

    public void interact(Particle particle, double maximumDistance, float maxOpacity, int color) {
        double dist = ((Vec2fDuck) locationVec).distance(particle.getLocationVec());

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
            realizedVelocity = new Vec2f(
                baseVelocity.x + RandomUtils.getRandomFloat() - 0.5F,
                baseVelocity.y + RandomUtils.getRandomFloat() - 0.5F
            );
        }

        float ms = speed / 2.0F;

        ((Vec2fDuck) locationVec).set(locationVec.x + realizedVelocity.x * ms, locationVec.y + realizedVelocity.y * ms);
    }
    public void interactWithMouse(@Nullable Vec2f mouse, Vec2f windowSize, boolean bounce, float repelledRadius, float scale) {
        if (mouse == null) return;

        float scaledRepelledRadius = -10.0F * scale + repelledRadius;

        Vec2f tmp = new Vec2f(locationVec.x - mouse.x / scale, locationVec.y - mouse.y / scale);
        float dist_mouse = (float) Math.sqrt(tmp.x * tmp.x + tmp.y * tmp.y);

        Vec2f normVec = new Vec2f(tmp.x / dist_mouse, tmp.y / dist_mouse);
        float repelFactor = (float) MathHelper.clamp((1 / scaledRepelledRadius) * (-1 * Math.pow(dist_mouse / scaledRepelledRadius, 2) + 1) * scaledRepelledRadius * 100, 0, 50);

        Vec2f newPosition = ((Vec2fDuck) locationVec).add(((Vec2fDuck) normVec).multiply(repelFactor));

        if (bounce) {
            if (newPosition.x - radius > 0 && newPosition.x + radius < windowSize.x) ((Vec2fDuck) locationVec).setX(newPosition.x);
            if (newPosition.y - radius > 0 && newPosition.y + radius < windowSize.y) ((Vec2fDuck) locationVec).setY(newPosition.y);
        } else {
            ((Vec2fDuck) locationVec).set(newPosition.x, newPosition.y);
        }
    }
}


