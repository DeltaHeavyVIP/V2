package methods;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

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

    public static void output_method_2(ArrayList<Double> stack_a, ArrayList<Double> stack_b, ArrayList<Double> stack_e, ArrayList<Double> stack_x, int loop, int equation, String file_or_console) {
        if (file_or_console.equals("console")) {
            System.out.println("+-------------------------------------------------------------------------------------------------------+");
            System.out.printf("|%-12s|%-12s|%-12s|%-12s|%-12s|%-12s|%-12s|%-12s|\n",
                    "№", "a", "b", "x", "F(a)", "F(b)", "F(x)", "|xn+1 - xn|");
            for (int i = 0; i < loop; i++) {
                System.out.println("|---------------+---------------+---------------+" +
                        "---------------+---------------+---------------+---------------+---------------|");
                System.out.printf("|%-12d|%-12.3f|%-12.3f|%-12.3f|%-12.3f|%-12.3f|%-12.3f|%-12.3f|\n",
                        (i + 1), stack_a.get(i), stack_b.get(i), stack_x.get(i + 1), function(stack_a.get(i),equation), function(stack_b.get(i),equation), function(stack_x.get(i+1),equation), stack_e.get(i+1));
            }
            System.out.println("+-------------------------------------------------------------------------------------------------------+");
        } else {
            String answer = "+-------------------------------------------------------------------------------------------------------+\n";
            answer += String.format("|%-12s|%-12s|%-12s|%-12s|%-12s|%-12s|%-12s|%-12s|\n",
                    "№", "a", "b", "x", "F(a)", "F(b)", "F(x)", "|xn+1 - xn|");
            for (int i = 0; i < loop; i++) {
                answer += String.format("|------------+------------+------------+" +
                        "------------+------------+------------+------------+------------|\n");
                answer += String.format("|%-12d|%-12.3f|%-12.3f|%-12.3f|%-12.3f|%-12.3f|%-12.3f|%-12.3f|\n",
                        (i + 1), stack_a.get(i), stack_b.get(i), stack_x.get(i + 1), function(stack_a.get(i),equation), function(stack_b.get(i),equation), function(stack_x.get(i+1),equation), stack_e.get(i+1));
            }
            answer += "+-------------------------------------------------------------------------------------------------------+\n";
            try (FileWriter writer = new FileWriter("src/main/resources/output_1", false)) {
                writer.write(answer);
                writer.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void output_method_3(ArrayList<Double> stack_x, ArrayList<Double> stack_function_x, ArrayList<Double> stack_derivative_x, ArrayList<Double> stack_new_x, ArrayList<Double> stack_e, int loop, int equation, String file_or_console) {
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

    public static void output_method_5(Stack<Double> stack_x, Stack<Double> stack_function_x, Stack<Double> stack_function_fi_x, Stack<Double> stack_new_x, Stack<Double> stack_e, int equation, int loop, String file_or_console) {
        String answer = "";
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
        try (FileWriter writer = new FileWriter("src/main/resources/output_1", false)) {
            writer.write(answer);
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void output_method_5_error(String file_or_console, double q){
        String answer = "";
        if (file_or_console.equals("console")) {
            System.out.println("+-----------------------------------------+");
            System.out.println("Коэффициент сходимости превышает 1: q = " + q);
            System.out.println("+-----------------------------------------+");
        } else {
            answer =  "+-----------------------------------------+\n";
            answer += "Коэффициент сходимости превышает 1: q = " + q;
            answer += "\n+-----------------------------------------+\n";
        }
        try (FileWriter writer = new FileWriter("src/main/resources/output_1", false)) {
            writer.write(answer);
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
