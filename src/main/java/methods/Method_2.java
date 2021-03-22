package methods;

import java.util.Stack;

public class Method_2 {

    private double a;
    private double b;
    private double e;
    private double x;
    private String file_or_console;
    private boolean flag;
    private int loop = 0;

    private Stack<Double> stack_a = new Stack<>();
    private Stack<Double> stack_b = new Stack<>();
    private Stack<Double> stack_e = new Stack<>();
    private Stack<Double> stack_x = new Stack<>();

    public Method_2(double a, double b, double e, String file_or_console) {
        this.a = a;
        this.b = b;
        this.e = e;
        this.file_or_console = file_or_console;
    }

    public void do_it() {
        stack_x.push(0.0);
        get_x_0();
        while (function(a) * function(b) < 0 && Math.abs(function(x)) > e && Math.abs(x - stack_x.peek()) > e) {
            stack_a.push(a);
            stack_b.push(b);
            stack_x.push(x);
            System.out.println(x);
            if(function(a)>0 && function(x)>0){
                a=x;
            }else if(function(a)>0 && function(x)<0){
                b=x;
            }else if(function(a)<0 && function(x)>0){
                b=x;
            }else if (function(a)<0 && function(x)<0){
                a=x;
            }

            if (flag) {
                x = get_x(a, b);
            } else {
                x = get_x(b, a);
            }

            stack_e.push(Math.abs(x - stack_x.peek()));
            loop++;
        }
        //TODO print_out
    }

    private double get_x_0() {
        if (function(a) * second_derivative(a) > 0) {
            x = get_x(a, b);
            flag = true;
        } else{
            x = get_x(b, a);
            flag = false;
        }
        return x;
    }

    private double get_x(double left, double right) {
        return left - ((right - left) / (function(right) - function(left))) * function(left);
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
