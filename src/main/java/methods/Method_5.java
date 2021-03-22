package methods;

import java.util.Stack;

public class Method_5 {

    private double a;
    private double b;
    private double e;
    private double x;
    private String file_or_console;
    private int loop = 0;
    private double lambda;

    private Stack<Double> stack_function_x = new Stack<>();
    private Stack<Double> stack_function_fi_x = new Stack<>();
    private Stack<Double> stack_new_x = new Stack<>();
    Stack<Double> stack_e = new Stack<>();
    Stack<Double> stack_x = new Stack<>();

    public Method_5(double a, double b, double e, String file_or_console) {
        this.a = a;
        this.b = b;
        this.e = e;
        this.file_or_console = file_or_console;
    }

    public void do_it() {

        double q;
        if (derivative(a) >= derivative(b)) {
            lambda = -1 / derivative(a);
        } else {
            lambda = -1 / derivative(b);
        }

        stack_x.push(0.0);
        get_x_0();

        if (Math.abs(derivative(a)) >= Math.abs(derivative(b))) {
            q = Math.abs(1 + lambda * (derivative(a)));
        } else {
            q = Math.abs(1 + lambda * (derivative(b)));
        }
        if (q > 0.5 && q < 1) {
            e = (1 - q) * e / q;
        }

        if (q < 1) {
            while (Math.abs(x - stack_x.peek()) > e) {
                System.out.println(x);
                stack_x.push(x);
                stack_function_x.push(function(x));
                stack_function_fi_x.push(get_x(x));
                get_x(x);
                stack_new_x.push(x);
                stack_e.push(Math.abs(x - stack_x.peek()));
                loop++;
            }
        }
        //TODO System.out.println("Коэффициент сходимости превышает 1: q = " + q");
    }


    private double get_x_0() {
        if (function(b) * second_derivative(b) >= 0) {
            x = b;
        } else if (function(a) * second_derivative(a) >= 0) {
            x = a;
        }
        return x;
    }

    private double get_x(double x) {
        return x + lambda * function(x);
    }

    private double function(double x) {
        return x * x * x - 4.5 * x * x - 9.21 * x - 0.383;
    }

    private double derivative(double x) {
        return 3 * x * x - 9 * x - 9.21;
    }

    private double second_derivative(double x) {
        return 6 * x - 9;
    }
}
