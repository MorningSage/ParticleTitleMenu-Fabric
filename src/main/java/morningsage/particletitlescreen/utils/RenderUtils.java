package morningsage.particletitlescreen.utils;

import com.sun.javafx.geom.Vec2d;
import org.lwjgl.opengl.GL11;

public final class RenderUtils {

    public static void renderPoint(Vec2d location, float radius, int color, float opacity) {
        final float red   = (float)(color >> 16 & 255) / 255.0F;
        final float green = (float)(color >>  8 & 255) / 255.0F;
        final float blue  = (float)(color       & 255) / 255.0F;

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_POINT_SMOOTH);
        GL11.glHint(GL11.GL_POINT_SMOOTH_HINT, GL11.GL_NICEST);

        GL11.glPointSize(radius);
        GL11.glColor4f(red, green, blue, opacity);

        GL11.glBegin(GL11.GL_POINTS);
        GL11.glVertex2d(location.x, location.y);
        GL11.glEnd();

        GL11.glHint(GL11.GL_POINT_SMOOTH_HINT, GL11.GL_DONT_CARE);
        GL11.glDisable(GL11.GL_POINT_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
    }
    public static void renderLine(Vec2d start, Vec2d end, float width, int color, float opacity) {
        final float red   = (float)(color >> 16 & 255) / 255.0F;
        final float green = (float)(color >>  8 & 255) / 255.0F;
        final float blue  = (float)(color       & 255) / 255.0F;

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);

        GL11.glLineWidth(width);
        GL11.glColor4f(red, green, blue, opacity);

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2d(start.x, start.y);
        GL11.glVertex2d(end.x, end.y);
        GL11.glEnd();

        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_DONT_CARE);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
    }
}
