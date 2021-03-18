package morningsage.particletitlescreen;

import com.sun.javafx.geom.Vec2d;
import lombok.*;
import morningsage.particletitlescreen.utils.RandomUtils;
import org.lwjgl.opengl.GL11;

@Data @Builder
public class Particle {

    @NonNull Vec2d locationVec;
    @Builder.Default float opacity = 1.0F;
    @Builder.Default int color = 0xFFFFFFFF;
    @Builder.Default float radius = 5;
    @Builder.Default Vec2d baseVelocity = new Vec2d();
    @Builder.Default double speed = 0;

    Vec2d realizedVelocity = null;

    public void interact(Particle particle, double maximumDistance, float maxOpacity, int color) {
        double dist = locationVec.distance(particle.getLocationVec());

        if (dist > maximumDistance) return;

        final float red   = (float)(color >> 16 & 255) / 255.0F;
        final float green = (float)(color >>  8 & 255) / 255.0F;
        final float blue  = (float)(color       & 255) / 255.0F;
        final float alpha = (float)(maxOpacity - (dist / (1 / maxOpacity)) / maximumDistance);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);

        GL11.glLineWidth(1.0f);
        GL11.glColor4f(red, green, blue, alpha);

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2d(locationVec.x, locationVec.y);
        GL11.glVertex2d(particle.locationVec.x, particle.locationVec.y);
        GL11.glEnd();

        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_DONT_CARE);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public void draw() {
        final float red   = (float)(color >> 16 & 255) / 255.0F;
        final float green = (float)(color >>  8 & 255) / 255.0F;
        final float blue  = (float)(color       & 255) / 255.0F;

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_POINT_SMOOTH);
        GL11.glHint(GL11.GL_POINT_SMOOTH_HINT, GL11.GL_NICEST);

        GL11.glPointSize(radius);
        GL11.glColor4f(red, green, blue, opacity);

        GL11.glBegin(GL11.GL_POINTS);
        GL11.glVertex2d(locationVec.x, locationVec.y);
        GL11.glEnd();

        GL11.glHint(GL11.GL_POINT_SMOOTH_HINT, GL11.GL_DONT_CARE);
        GL11.glDisable(GL11.GL_POINT_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
    }
    public void move() {
        if (realizedVelocity == null) {
            realizedVelocity = new Vec2d(baseVelocity.x + RandomUtils.getRandomFloat(0, 1) - 0.5, baseVelocity.y + RandomUtils.getRandomFloat(0, 1) - 0.5);
        }

        double ms = speed / 2;

        locationVec.set(locationVec.x + realizedVelocity.x * ms, locationVec.y + realizedVelocity.y * ms);
    }
}


