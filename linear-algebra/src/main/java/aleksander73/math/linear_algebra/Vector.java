package aleksander73.math.linear_algebra;

public class Vector
{
    private final float[] values;

    public Vector(float[] values) {
        this.values = values;
    }

    public Vector add(Vector v) {
        float[] values = new float[this.dimensions()];
        for(int i = 0; i < this.dimensions(); i++) {
            values[i] = this.values[i] + v.values[i];
        }

        return new Vector(values);
    }

    public Vector sub(Vector v) {
        return this.add(v.negate());
    }

    public Vector mul(float c) {
        float[] values = new float[this.dimensions()];
        for(int i = 0; i < this.dimensions(); i++) {
            values[i] = this.values[i] * c;
        }

        return new Vector(values);
    }

    public float dotProduct(Vector v) {
        float dotProduct = 0.0f;
        for(int i = 0; i < this.dimensions(); i++) {
            dotProduct += values[i] * v.values[i];
        }

        return dotProduct;
    }

    public Vector hadamardProduct(Vector v) {
        float[] values = new float[this.dimensions()];
        for(int i = 0; i < this.dimensions(); i++) {
            values[i] = this.values[i] * v.values[i];
        }

        return new Vector(values);
    }

    public Vector negate() {
        float[] values = new float[this.dimensions()];
        for(int i = 0; i < this.dimensions(); i++) {
            values[i] = -this.values[i];
        }

        return new Vector(values);
    }

    public float magnitude() {
        float sum = 0.0f;
        for(int i = 0; i < this.dimensions(); i++) {
            sum += Math.pow(values[i], 2);
        }

        return (float)Math.sqrt(sum);
    }

    public Vector normalize() {
        float[] values = new float[this.dimensions()];
        float m = this.magnitude();
        for(int i = 0; i < this.dimensions(); i++) {
            values[i] = this.values[i] / m;
        }

        return new Vector(values);
    }

    public Vector resize(float length) {
        return this.normalize().mul(length);
    }

    public float distance(Vector v) {
        return this.sub(v).magnitude();
    }

    public Vector lerp(Vector target, float r) {
        return this.add(target.sub(this).mul(r));
    }

    public int dimensions() {
        return values.length;
    }

    public Vector copy() {
        float[] values = new float[this.dimensions()];
        for(int i = 0; i < this.dimensions(); i++) {
            values[i] = this.values[i];
        }

        return new Vector(values);
    }

    public float[] getValues() {
        return values;
    }

    public float getValue(int i) {
        return values[i];
    }

    private Vector toVector(int n) {
        float[] values = new float[n];

        int len = this.values.length;
        int lim = (n <= len) ? n : len;
        for(int i = 0; i < lim; i++) {
            values[i] = this.getValue(i);
        }

        return new Vector(values);
    }

    public Vector2d toVector2d() {
        Vector v = this.toVector(2);
        return new Vector2d(v.getValue(0), v.getValue(1));
    }

    public Vector3d toVector3d() {
        Vector v = this.toVector(3);
        return new Vector3d(v.getValue(0), v.getValue(1), v.getValue(2));
    }

    public Vector4d toVector4d() {
        Vector v = this.toVector(4);
        return new Vector4d(v.getValue(0), v.getValue(1), v.getValue(2), v.getValue(3));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        for(int i = 0; i < values.length; i++) {
            sb.append(values[i]);
            if(i < values.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");

        return sb.toString();
    }
}
