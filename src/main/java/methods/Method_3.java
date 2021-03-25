package methods;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static methods.Function.*;

public class Method_3 {

    private double a;
    private double b;
    private double e;
    private double x;
    private int equation;
    private String file_or_console;
    private int loop = 0;

    private ArrayList<Double> stack_function_x = new ArrayList<>();
    private ArrayList<Double> stack_derivative_x = new ArrayList<>();
    private ArrayList<Double> stack_new_x = new ArrayList<>();
    private ArrayList<Double> stack_e = new ArrayList<>();
    private ArrayList<Double> stack_x = new ArrayList<>();

    public Method_3(double a, double b, double e, int equation, String file_or_console) {
        this.equation = equation;
        this.a = a;
        this.b = b;
        this.e = e;
        this.file_or_console = file_or_console;
    }

    private double get_x_0() {
        if (function(b,equation) * second_derivative(b,equation) >= 0) {
            x = b;
        } else{
            x = a;
        }
        return x;
    }

    private double get_x(double x) {
        return x - (function(x,equation) / derivative(x,equation));
    }

    public void do_it() {

        stack_x.add(0.0);
        get_x_0();
        while (function(a,equation) * function(b,equation) < 0 && derivative(x,equation) != 0 && Math.abs(function(x,equation)) > e && Math.abs(x - stack_x.get(loop)) > e && Math.abs(function(x,equation) / derivative(x,equation)) > e) {
            stack_x.add(x);
            stack_function_x.add(function(x,equation));
            stack_derivative_x.add(derivative(x,equation));
            x = get_x(x);
            stack_new_x.add(x);
            stack_e.add(Math.abs(x - stack_x.get(loop)));
            loop++;
        }
        stack_x.add(x);
        stack_function_x.add(function(x,equation));
        stack_derivative_x.add(derivative(x,equation));
        x = get_x(x);
        stack_new_x.add(x);
        stack_e.add(Math.abs(0.0));

        if (file_or_console.equals("console")) {
            System.out.println("+-----------------------------------------------------------------------------+");
            System.out.printf("|%-12s|%-12s|%-12s|%-12s|%-12s|%-12s|\n",
                    "№", "xk", "f(xk)", "f'(xk)", "xk+1","|xk - xk+1|");
            for (int i = 0; i < loop+1; i++) {
                System.out.println("|------------+------------+------------+" +
                        "------------+------------+------------|");
                System.out.printf("|%-12s|%-12.3f|%-12.3f|%-12.3f|%-12.3f|%-12.3f|\n",
                        (i+1), stack_x.get(i+1), stack_function_x.get(i), stack_derivative_x.get(i),stack_new_x.get(i),stack_e.get(i));
            }
            System.out.println("+-----------------------------------------------------------------------------+");
        } else {
            String answer = "+-----------------------------------------------------------------------------+\n";
            answer += String.format("|%-12s|%-12s|%-12s|%-12s|%-12s|%-12s|\n",
                    "№", "xk", "f(xk)", "f'(xk)", "xk+1","|xk - xk+1|");
            for (int i = 0; i < loop+1; i++) {
                answer += String.format("|------------+------------+------------+" +
                        "------------+------------+------------|\n");
                answer += String.format("|%-12s|%-12.3f|%-12.3f|%-12.3f|%-12.3f|%-12.3f|\n",
                        (i+1), stack_x.get(i+1), stack_function_x.get(i), stack_derivative_x.get(i),stack_new_x.get(i),stack_e.get(i));
            }
            answer += String.format("+-----------------------------------------------------------------------------+\n");
            try(FileWriter writer = new FileWriter("src/main/resources/output_1", false)){
                writer.write(answer);
                writer.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
