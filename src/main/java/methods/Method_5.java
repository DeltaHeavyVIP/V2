package methods;

import java.util.Stack;

import static methods.Function.function;
import static methods.Function.second_derivative;

public class Method_5 {

    private double a;
    private double b;
    private double e;
    private double x;
    private int equation;
    private String file_or_console;
    private int loop = 0;
    private double lambda;

    private Stack<Double> stack_function_x = new Stack<>();
    private Stack<Double> stack_function_fi_x = new Stack<>();
    private Stack<Double> stack_new_x = new Stack<>();
    Stack<Double> stack_e = new Stack<>();
    Stack<Double> stack_x = new Stack<>();

    public Method_5(double a, double b, double e, int equation, String file_or_console) {
        this.equation = equation;
        this.a = a;
        this.b = b;
        this.e = e;
        this.file_or_console = file_or_console;
    }

    private double get_x_0() {
        if (function(b, equation) * second_derivative(b, equation) >= 0) {
            x = b;
        } else {
            x = a;
        }
        return x;
    }

    private double Fi() {
        return x + lambda * Function.function(x, equation);
    }

    public double Fi_(double x) {
        return 1 + lambda * Function.derivative(x, equation);
    }

    public double getMaxQ(double a, double b) {
        return Math.max(Math.abs(Fi_(a)), Math.abs(Fi_(b)));
    }

    public static double getMaxF(double a, double b, int number) {
        return Math.max(Function.derivative(a, number), Function.derivative(b, number));
    }

    public void do_it() {

        lambda = -1 / getMaxF(a, b, equation);
        x = get_x_0();
        stack_x.add(x);
        stack_function_x.add(Function.function(x, equation));
        stack_function_fi_x.add(Fi());
        x = Fi();
        stack_new_x.add(x);
        stack_e.add(Math.abs(x - stack_x.get(loop)));
        double q = getMaxQ(a, b);

        if (q < 1) {
            if (q > 0.5 && q < 1) {
                e = (1 - q) * e / q;
            }

            while (stack_e.get(loop) > e) {
                stack_x.add(x);
                stack_function_x.add(Function.function(x, equation));
                stack_function_fi_x.add(Fi());
                x = Fi();
                stack_new_x.add(x);
                stack_e.add(Math.abs(x - stack_x.get(loop)));
                loop++;
            }

            Function.output_method_5(stack_x,stack_function_x,stack_function_fi_x,stack_new_x,stack_e,equation,loop,file_or_console);
        } else {
            Function.output_method_5_error(file_or_console, q);
        }
    }
    public double getLambda(){return lambda;}
}