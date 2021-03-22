package methods;

import java.util.Stack;

public class Method_3 {

    private double a;
    private double b;
    private double e;
    private double x;
    private String file_or_console;
    private int loop = 0;

    private Stack<Double> stack_function_x = new Stack<>();
    private Stack<Double> stack_derivative_x = new Stack<>();
    private Stack<Double> stack_new_x = new Stack<>();
    Stack<Double> stack_e = new Stack<>();
    Stack<Double> stack_x = new Stack<>();

    public Method_3(double a, double b, double e, String file_or_console) {
        this.a = a;
        this.b = b;
        this.e = e;
        this.file_or_console = file_or_console;
    }

    public void do_it() {  //TODO peredelat
        stack_x.push(0.0);
        get_x_0();
        while (function(a) * function(b) < 0 && derivative(x) != 0 && Math.abs(function(x)) > e && Math.abs(x - stack_x.peek()) > e && Math.abs(function(x) / derivative(x)) > e) {
            System.out.println(x);
            stack_x.push(x);
            stack_function_x.push(function(x));
            stack_derivative_x.push(derivative(x));
            x = get_x(x);
            stack_new_x.push(x);
            stack_e.push(Math.abs(x - stack_x.peek()));
            loop++;
            System.out.println(stack_x.peek());
        }
        //TODO print_out
    }

    private double get_x_0() {
        if (function(b) * second_derivative(b) >= 0) {
            x = b;
        } else{
            x = a;
        }
        return x;
    }

    private double get_x(double x) {
        return x - (function(x) / derivative(x));
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
