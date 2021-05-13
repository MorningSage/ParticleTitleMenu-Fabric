package morningsage.particletitlescreen.ducks;

import net.minecraft.util.math.Vec2f;

public interface Vec2fDuck {
    void set(float x, float y);
    void setX(float x);
    float getX();
    void setY(float y);
    float getY();

    double distance(Vec2f v);
    Vec2f multiply(float factor);
    Vec2f add(Vec2f addend);
}
