package methods;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Method_3 {

    private double a;
    private double b;
    private double e;
    private double x;
    private String file_or_console;
    private int loop = 0;

    private ArrayList<Double> stack_function_x = new ArrayList<>();
    private ArrayList<Double> stack_derivative_x = new ArrayList<>();
    private ArrayList<Double> stack_new_x = new ArrayList<>();
    private ArrayList<Double> stack_e = new ArrayList<>();
    private ArrayList<Double> stack_x = new ArrayList<>();

    public Method_3(double a, double b, double e, String file_or_console) {
        this.a = a;
        this.b = b;
        this.e = e;
        this.file_or_console = file_or_console;
    }

    public void do_it() {
        stack_x.add(0.0);
        get_x_0();
        while (function(a) * function(b) < 0 && derivative(x) != 0 && Math.abs(function(x)) > e && Math.abs(x - stack_x.get(loop)) > e && Math.abs(function(x) / derivative(x)) > e) {
            stack_x.add(x);
            stack_function_x.add(function(x));
            stack_derivative_x.add(derivative(x));
            x = get_x(x);
            stack_new_x.add(x);
            stack_e.add(Math.abs(x - stack_x.get(loop)));
            loop++;
        }
        if (file_or_console.equals("console")) {
            System.out.println("+-------------------------------------------------" +
                    "----------------------------------------------+");
            System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                    "№", "xk", "f(xk)", "f'(xk)", "xk+1"," |xk - xk+1| ");
            for (int i = 0; i < loop; i++) {
                System.out.println("|---------------+---------------+---------------+" +
                        "---------------+---------------+---------------|");
                System.out.printf("|%-15d|%-15f|%-15f|%-15f|%-15f|%-15f|\n",
                        (i + 1), stack_x.get(i), stack_function_x.get(i), stack_derivative_x.get(i),stack_new_x.get(i),stack_e.get(i));
            }
            System.out.println("+------------------------------------------------" +
                    "-----------------------------------------------+");
        } else {
            String answer = "+-------------------------------------------------" +
                    "----------------------------------------------+\n";
            answer += String.format("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                    "№", "xk", "f(xk)", "f'(xk)", "xk+1"," |xk - xk+1| ");
            for (int i = 0; i < loop; i++) {
                answer += String.format("|---------------+---------------+---------------+" +
                        "---------------+---------------+---------------|\n");
                answer += String.format("|%-15d|%-15f|%-15f|%-15f|%-15f|%-15f|\n",
                        (i + 1), stack_x.get(i), stack_function_x.get(i), stack_derivative_x.get(i),stack_new_x.get(i),stack_e.get(i));
            }
            answer += String.format("+------------------------------------------------" +
                    "-----------------------------------------------+\n");
            try(FileWriter writer = new FileWriter("src/main/resources/output_1", false)){
                writer.write(answer);
                writer.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
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
