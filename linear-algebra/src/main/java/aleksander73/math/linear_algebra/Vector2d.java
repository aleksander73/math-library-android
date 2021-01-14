package aleksander73.math.linear_algebra;

public class Vector2d extends Vector
{
    public Vector2d(float x, float y) {
        super(new float[] {x, y});
    }

    public float getX() {
        return super.getValue(0);
    }

    public float getY() {
        return super.getValue(1);
    }
}
