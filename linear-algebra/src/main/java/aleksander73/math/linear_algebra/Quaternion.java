package aleksander73.math.linear_algebra;

public class Quaternion
{
    private final float w;
    private final float x;
    private final float y;
    private final float z;

    public Quaternion(float w, float x, float y, float z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Quaternion(float scalar, Vector3d vector) {
        w = scalar;
        x = vector.getX();
        y = vector.getY();
        z = vector.getZ();
    }

    public Quaternion hamiltonProduct(Quaternion q) {
        Vector3d pv = this.vector();
        Vector3d qv = q.vector();

        float s = w * q.w - pv.dotProduct(qv);
        Vector3d v = qv.mul(w).add(pv.mul(q.w)).add(pv.crossProduct(qv)).toVector3d();

        return new Quaternion(s, v);
    }

    public Quaternion conjugate() {
        return new Quaternion(w, -x, -y, -z);
    }

    public Vector3d vector() {
        return new Vector3d(x, y, z);
    }

    public static Quaternion toRotationQuaternion(Vector3d axis, float angle) {
        float cos = (float)Math.cos(Math.toRadians(angle / 2.0f));
        float sin = (float)Math.sin(Math.toRadians(angle / 2.0f));

        return new Quaternion(cos, axis.mul(sin).toVector3d());
    }

    public Vector3d toEulerAngles() {
        float t = 2.0f * (w * y - x * z);
        t = (t > 1.0f) ? 1.0f : (t < -1.0f) ? -1.0f : t;

        float rotX = (float)Math.atan2(2.0f * (w * x + y * z), 1.0f - 2.0f * (x * x + y * y));
        float rotY = (float)Math.asin(t);
        float rotZ = (float)Math.atan2(2.0f * (w * z + x * y), 1.0f - 2.0f * (y * y + z * z));

        rotX = (float)Math.toDegrees(rotX);
        rotY = (float)Math.toDegrees(rotY);
        rotZ = (float)Math.toDegrees(rotZ);

        return new Vector3d(rotX, rotY, rotZ);
    }
}
