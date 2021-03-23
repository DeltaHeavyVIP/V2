package methods;

public class Function {

    public static double function(double x) {
        return x * x * x - 4.5 * x * x - 9.21 * x - 0.383;
    }

    public static double derivative(double x) {
        return 3 * x * x - 9 * x - 9.21;
    }

    public static double second_derivative(double x) {
        return 6 * x - 9;
    }
}
