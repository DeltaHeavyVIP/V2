package methods;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Method_2 {

    private double a;
    private double b;
    private double e;
    private double x;
    private String file_or_console;
    private boolean flag;
    private int loop = 0;

    private ArrayList<Double> stack_a = new ArrayList<>();
    private ArrayList<Double> stack_b = new ArrayList<>();
    private ArrayList<Double> stack_e = new ArrayList<>();
    private ArrayList<Double> stack_x = new ArrayList<>();

    public Method_2(double a, double b, double e, String file_or_console) {
        this.a = a;
        this.b = b;
        this.e = e;
        this.file_or_console = file_or_console;
    }

    public void do_it() {
        stack_x.add(0.0);
        get_x_0();
        while (function(a) * function(b) < 0 && Math.abs(function(x)) > e && Math.abs(x - stack_x.get(loop)) > e) {
            stack_a.add(a);
            stack_b.add(b);
            stack_x.add(x);
            if (function(a) > 0 && function(x) > 0) {
                a = x;
            } else if (function(a) > 0 && function(x) < 0) {
                b = x;
            } else if (function(a) < 0 && function(x) > 0) {
                b = x;
            } else if (function(a) < 0 && function(x) < 0) {
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
        if (file_or_console.equals("console")) {
            System.out.println("+-------------------------------------------------" +
                    "------------------------------------------------------------------------------+");
            System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                    "№", "a", "b", "x", "F(a)", "F(b)", "F(x)", " |xn+1 - xn| ");
            for (int i = 0; i < loop; i++) {
                System.out.println("|---------------+---------------+---------------+" +
                        "---------------+---------------+---------------+---------------+---------------|");
                System.out.printf("|%-15d|%-15f|%-15f|%-15f|%-15f|%-15f|%-15f|%-15f|\n",
                        (i + 1), stack_a.get(i), stack_b.get(i), stack_x.get(i + 1), function(stack_a.get(i)), function(stack_b.get(i)), function(stack_x.get(i)), stack_e.get(i));
            }
            System.out.println("+-------------------------------------------------" +
                    "------------------------------------------------------------------------------+");
        } else {
            String answer = "+-------------------------------------------------" +
                    "------------------------------------------------------------------------------+\n";
            answer += String.format("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                    "№", "a", "b", "x", "F(a)", "F(b)", "F(x)", " |xn+1 - xn| ");
            for (int i = 0; i < loop; i++) {
                answer += String.format("|---------------+---------------+---------------+" +
                        "---------------+---------------+---------------+---------------+---------------|\n");
                answer += String.format("|%-15d|%-15f|%-15f|%-15f|%-15f|%-15f|%-15f|%-15f|\n",
                        (i + 1), stack_a.get(i), stack_b.get(i), stack_x.get(i + 1), function(stack_a.get(i)), function(stack_b.get(i)), function(stack_x.get(i)), stack_e.get(i));
            }
            answer += "+-------------------------------------------------" +
                    "------------------------------------------------------------------------------+\n";
            try (FileWriter writer = new FileWriter("src/main/resources/output_1", false)) {
                writer.write(answer);
                writer.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private double get_x_0() {
        if (function(a) * second_derivative(a) > 0) {
            x = get_x(a, b);
            flag = true;
        } else {
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
