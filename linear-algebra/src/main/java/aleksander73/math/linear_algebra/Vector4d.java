package aleksander73.math.linear_algebra;

public class Vector4d extends Vector
{
    public Vector4d(float w, float x, float y, float z) {
        super(new float[] {w, x, y, z});
    }

    public float getW() {
        return super.getValue(0);
    }

    public float getX() {
        return super.getValue(1);
    }

    public float getY() {
        return super.getValue(2);
    }

    public float getZ() {
        return super.getValue(3);
    }
}
