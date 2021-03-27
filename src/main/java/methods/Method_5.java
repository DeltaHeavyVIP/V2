package methods;

import java.io.FileWriter;
import java.io.IOException;
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
    private String answer = "";

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

            if (file_or_console.equals("console")) {
                System.out.println("+-----------------------------------------------------------------------------+");
                System.out.printf("|%-12s|%-12s|%-12s|%-12s|%-12s|%-12s|\n",
                        "№", "xk", "f(xk)", "xk+1", "ф(xk)", "|xk - xk+1|");
                for (int i = 0; i < loop + 1; i++) {
                    System.out.println("|------------+------------+------------+" +
                            "------------+------------+------------|");
                    System.out.printf("|%-12s|%-12.3f|%-12.3f|%-12.3f|%-12.3f|%-12.3f|\n",
                            (i + 1), stack_x.get(i), stack_function_x.get(i), stack_new_x.get(i), stack_function_fi_x.get(i), stack_e.get(i));
                }
                System.out.println("+-----------------------------------------------------------------------------+");
            } else {
                answer = "+-----------------------------------------------------------------------------+\n";
                answer += String.format("|%-12s|%-12s|%-12s|%-12s|%-12s|%-12s|\n",
                        "№", "xk", "f(xk)", "xk+1", "ф(xk)", "|xk - xk+1|");
                for (int i = 0; i < loop + 1; i++) {
                    answer += String.format("|------------+------------+------------+" +
                            "------------+------------+------------|\n");
                    answer += String.format("|%-12s|%-12.3f|%-12.3f|%-12.3f|%-12.3f|%-12.3f|\n",
                            (i + 1), stack_x.get(i), stack_function_x.get(i), stack_new_x.get(i), stack_function_fi_x.get(i), stack_e.get(i));
                }
                answer += String.format("+-----------------------------------------------------------------------------+\n");
            }
        } else {
            if (file_or_console.equals("console")) {
                System.out.println("+-----------------------------------------+");
                System.out.println("Коэффициент сходимости превышает 1: q = " + q);
                System.out.println("+-----------------------------------------+");
            } else {
                answer =  "+-----------------------------------------+\n";
                answer += "Коэффициент сходимости превышает 1: q = " + q;
                answer += "\n+-----------------------------------------+\n";

            }
        }
        try (FileWriter writer = new FileWriter("src/main/resources/output_1", false)) {
            writer.write(answer);
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public double getLambda(){return lambda;}
}