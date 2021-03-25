package methods;

public class Function {

    public static double function(double x,int equation) {
        if (equation == 1) {
            return x * x * x - 4.5 * x * x - 9.21 * x - 0.383;
        } else if (equation == 2) {
            return Math.sin(x);
        } else {
            return x*x*x - x + 4;
        }
    }

    public static double derivative(double x,int equation) {
        if (equation == 1) {
            return 3 * x * x - 9 * x - 9.21;
        } else if (equation == 2) {
            return Math.cos(x);
        } else {
            return 3*x*x - 1;
        }
    }

    public static double second_derivative(double x,int equation) {
        if (equation == 1) {
            return 6 * x - 9;
        } else if (equation == 2) {
            return -1*Math.sin(x);
        } else {
            return 6 * x;
        }
    }
}
