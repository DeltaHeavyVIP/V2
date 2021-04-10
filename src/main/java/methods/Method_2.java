package methods;


import java.util.ArrayList;

import static methods.Function.function;
import static methods.Function.second_derivative;

public class Method_2 {

    private double a;
    private double b;
    private double e;
    private double x;
    private int equation;
    private String file_or_console;
    private boolean flag;
    private int loop = 0;

    private ArrayList<Double> stack_a = new ArrayList<>();
    private ArrayList<Double> stack_b = new ArrayList<>();
    private ArrayList<Double> stack_e = new ArrayList<>();
    private ArrayList<Double> stack_x = new ArrayList<>();

    public Method_2(double a, double b, double e, int equation, String file_or_console) {
        this.equation = equation;
        this.a = a;
        this.b = b;
        this.e = e;
        this.file_or_console = file_or_console;
    }

    private double get_x_0() {
        if (function(a,equation) * second_derivative(a,equation) > 0) {
            x = get_x(a, b);
            flag = true;
        } else {
            x = get_x(b, a);
            flag = false;
        }
        return x;
    }

    private double get_x(double left, double right) {
        return left - ((right - left) / (function(right,equation) - function(left,equation))) * function(left,equation);
    }

    public void do_it() {
        stack_x.add(0.0);
        get_x_0();
        while (function(a,equation) * function(b,equation) < 0 && Math.abs(function(x,equation)) > e && Math.abs(x - stack_x.get(loop)) > e) {
            stack_a.add(a);
            stack_b.add(b);
            stack_x.add(x);
            if (function(a,equation) > 0 && function(x,equation) > 0) {
                a = x;
            } else if (function(a,equation) > 0 && function(x,equation) < 0) {
                b = x;
            } else if (function(a,equation) < 0 && function(x,equation) > 0) {
                b = x;
            } else if (function(a,equation) < 0 && function(x,equation) < 0) {
                a = x;
            }

            if (flag) {
                x = get_x(a, b);
            } else {
                x = get_x(b, a);
            }

            stack_e.add(Math.abs(x - stack_x.get(loop)));
            loop++;
        }
        stack_e.add(Math.abs(x - stack_x.get(loop)));
        Function.output_method_2(stack_a,stack_b,stack_e,stack_x,loop,equation,file_or_console);
    }
}
