package aleksander73.math.linear_algebra;

public class Matrix
{
    private final int rows;
    private final int columns;
    private final float[] values;

    private Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.values = new float[rows * columns];
    }

    public Matrix(int rows, int columns, float[] values) {
        this.rows = rows;
        this.columns = columns;
        this.values = values;
    }

    public static Matrix identity(int d) {
        Matrix identity = new Matrix(d, d);
        for(int i = 0; i < d; i++) {
            identity.setValue(i, i, 1.0f);
        }

        return identity;
    }

    /**
     * Matrices with dimensions a x b and c x d are compatible for multiplication if and only if b = c
     */
    public Matrix mul(Matrix m) {
        Vector[] rowVectors = new Vector[rows];
        for(int r = 0; r < rows; r++) {
            rowVectors[r] = this.rowVector(r);
        }

        Vector[] columnVectors = new Vector[m.columns];
        for(int c = 0; c < m.columns; c++) {
            columnVectors[c] = m.columnVector(c);
        }

        Matrix result = new Matrix(rows, m.columns);
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < m.columns; c++) {
                result.setValue(r, c, rowVectors[r].dotProduct(columnVectors[c]));
            }
        }

        return result;
    }

    public Matrix transpose() {
        Matrix transposed = new Matrix(columns, rows);
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < columns; c++) {
                transposed.setValue(r, c, this.getValue(c, r));
            }
        }

        return transposed;
    }

    public Vector rowVector(int row) {
        float[] values = new float[columns];
        for(int i = 0; i < columns; i++) {
            values[i] = this.getValue(row, i);
        }

        return new Vector(values);
    }

    public Vector columnVector(int column) {
        float[] values = new float[rows];
        for(int i = 0; i < rows; i++) {
            values[i] = this.getValue(i, column);
        }

        return new Vector(values);
    }

    public float[] getValues() {
        return values;
    }

    public float getValue(int r, int c) {
        return values[r * columns + c];
    }

    private void setValue(int r, int c, float value) {
        values[r * columns + c] = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < columns; c++) {
                sb.append(this.getValue(r, c));
                if(c < columns - 1) {
                    sb.append(", ");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
