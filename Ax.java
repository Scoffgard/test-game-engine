package fr.scoffgard;

public class Ax {

    static public Matrix3 XY(double value) {
        return new Matrix3(
                new double[] {
                        1, 0, 0,
                        0, Math.cos(value), Math.sin(value),
                        0, -Math.sin(value), Math.cos(value)
                }
        );
    }

    static public Matrix3 YZ(double value) {
        return new Matrix3(
                new double[] {
                        1, 0, 0,
                        0, Math.cos(value), Math.sin(value),
                        0, -Math.sin(value), Math.cos(value)
                }
        );
    }

    static public Matrix3 XZ(double value) {
        return new Matrix3(
                new double[] {
                        Math.cos(value), 0, -Math.sin(value),
                        0, 1, 0,
                        Math.sin(value), 0, Math.cos(value)
                }
        );
    }
}
