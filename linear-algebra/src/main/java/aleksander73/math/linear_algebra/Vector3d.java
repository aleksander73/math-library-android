package aleksander73.math.linear_algebra;

public class Vector3d extends Vector
{
    public static final Vector3d zeroVector = new Vector3d(0.0f, 0.0f, 0.0f);

    public static final Vector3d xUnitVector = new Vector3d(1.0f, 0.0f, 0.0f);
    public static final Vector3d yUnitVector = new Vector3d(0.0f, 1.0f, 0.0f);
    public static final Vector3d zUnitVector = new Vector3d(0.0f, 0.0f, 1.0f);

    public Vector3d(float x, float y, float z) {
        super(new float[] {x, y, z});
    }

    public Vector3d crossProduct(Vector3d v) {
        return new Vector3d(
                this.getY() * v.getZ() - this.getZ() * v.getY(),
                this.getZ() * v.getX() - this.getX() * v.getZ(),
                this.getX() * v.getY() - this.getY() * v.getX()
        );
    }

    public float angle(Vector3d v) {
        if(this.magnitude() * v.magnitude() == 0.0f) {
            return 0.0f;
        }

        float dotProduct = this.normalize().dotProduct(v.normalize());
        if(dotProduct > 1.0f) {
            dotProduct = 1.0f;
        } else if(dotProduct < -1.0f) {
            dotProduct = -1.0f;
        }

        return (float)Math.toDegrees(Math.acos(dotProduct));
    }

    public Vector3d rotate(Vector3d axis, float angle) {
        Vector3d axis_ = axis.normalize().toVector3d();
        float angle_ = angle % 360.0f;
        Quaternion q = Quaternion.toRotationQuaternion(axis_, angle_);

        Quaternion rotated = q.hamiltonProduct(this.toQuaternion()).hamiltonProduct(q.conjugate());
        return rotated.vector();
    }

    public Vector3d transform(Matrix m) {
        Matrix vm = this.toMatrix4x1();
        Matrix result = m.mul(vm);

        return result.columnVector(0).toVector3d();
    }

    public Matrix toMatrix4x1() {
        return new Matrix(4, 1, new float[] {
                this.getX(), this.getY(), this.getZ(), 1.0f
        });
    }

    public Quaternion toQuaternion() {
        return new Quaternion(0.0f, this);
    }

    public float getX() {
        return super.getValue(0);
    }

    public float getY() {
        return super.getValue(1);
    }

    public float getZ() {
        return super.getValue(2);
    }

    @Override
    public String toString() {
        return "[" + this.getX() + ", "  + this.getY() + ", " + this.getZ() + "]";
    }
}
