import exception.DerivativesExeption;
import exception.InaccuracyException;
import exception.MuchRootException;
import exception.NullRootException;
import methods.Function;
import methods.Method_2;
import methods.Method_3;
import methods.Method_5;

import java.io.*;
import java.util.Scanner;

public class Main {
    private static final String path_to_file = "src/main/resources/input_1";

    public static void main(String[] args) throws IOException {
        double a = 0, b = 0, e = 0;
        int met = 0, equation = 0;
        Scanner inConsole = new Scanner(System.in);

        System.out.println("Введите \"file\" для ввода с файла \n" +
                "               или               \n" +
                " \"console\" для ввода с консоли  ");
        String consol_or_file = "file";//TODO inConsole.next();

        while (!consol_or_file.equals("console") && !consol_or_file.equals("file")) {
            System.out.println("Ну я же попросил!");
            consol_or_file = inConsole.next();
        }

        if (consol_or_file.equals("console")) {
            for (; ; ) {
                System.out.println("Введите значение левой границы:");
                try {
                    a = Double.parseDouble(inConsole.next().trim().replaceAll(",", "\\."));
                    break;
                } catch (NumberFormatException ex) {
                    System.out.println("Ну и зачем ты ввел некорректные данные, давай по новой...");
                }
            }
            for (; ; ) {
                System.out.println("Введите значение правой границы:");
                try {
                    b = Double.parseDouble(inConsole.next().trim().replaceAll(",", "\\."));
                    break;
                } catch (NumberFormatException ex) {
                    System.out.println("Ну и зачем ты ввел некорректные данные, давай по новой...");
                }
            }
            for (; ; ) {
                System.out.println("Введите значение погрешности:");
                try {
                    e = Double.parseDouble(inConsole.next().trim().replaceAll(",", "\\."));
                    break;
                } catch (NumberFormatException ex) {
                    System.out.println("Ну и зачем ты ввел некорректные данные, давай по новой...");
                }
            }
            for (; ; ) {
                System.out.println("Какой уравение ты хочешь использовать, могу предложить:\n" +
                        "1) y = x^3 - 4.5x^2 - 9.21x - 0.383 (введи 1)\n" +
                        "2)          y = sin(x)              (введи 2)\n" +
                        "3)          y = x^3-x+4             (введи 3)\n");
                try {
                    equation = Integer.parseInt(inConsole.next().trim());
                    if (equation == 1 || equation == 2 || equation == 3) {
                        break;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Ну и зачем ты ввел некорректные данные, давай по новой...");
                }
            }
            for (; ; ) {
                System.out.println("Какой метод ты хочешь использовать, могу предложить:\n" +
                        "1)Метод хорд (введи 2)\n" +
                        "2)Метод Ньютона (введи 3)\n" +
                        "3)Метод простой итерации (введи 5)\n");
                try {
                    met = Integer.parseInt(inConsole.next().trim());
                    break;
                } catch (NumberFormatException ex) {
                    System.out.println("Ну и зачем ты ввел некорректные данные, давай по новой...");
                }
            }
            for (; ; ) {
                System.out.println("Куда сделаем вывод данных?\n Введи \'file\' для вывода в файл или введи \'console\' для вывода в консоль");
                try {
                    consol_or_file = inConsole.next().trim();
                    if (!consol_or_file.equals("console") && !consol_or_file.equals("file")) {
                        throw new NumberFormatException();
                    }
                    break;
                } catch (NumberFormatException ex) {
                    System.out.println("Ну и зачем ты ввел некорректные данные, давай по новой...");
                }
            }
            check_data_and_do_method(a, b, e, met, equation, consol_or_file);
        } else {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(new File(path_to_file)));
            } catch (FileNotFoundException ex) {
                System.out.println("Кривоватый путь к file");
                System.exit(0);
            }
            try {
                a = Double.parseDouble(reader.readLine().trim().replaceAll(",", "\\."));
                b = Double.parseDouble(reader.readLine().trim().replaceAll(",", "\\."));
                e = Double.parseDouble(reader.readLine().trim().replaceAll(",", "\\."));
                equation = Integer.parseInt(reader.readLine().trim());
                if (equation != 1 && equation != 2 && equation != 3) {
                    throw new NumberFormatException();
                }
                met = Integer.parseInt(reader.readLine().trim());
                consol_or_file = reader.readLine().trim();
                if (!consol_or_file.equals("console") && !consol_or_file.equals("file")) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ignored) {
                System.out.println("В файле кривые данные,поменяйте их и попробуйде снова");
                System.exit(0);
            }
            check_data_and_do_method(a, b, e, met, equation, consol_or_file);
        }
    }

    private static void check_data_and_do_method(double a, double b, double e, int met, int equation, String consol_or_file) {
        try {
            data_is_norm(a, b, e, equation);
        } catch (InaccuracyException | MuchRootException | NullRootException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        } catch (DerivativesExeption ex) {
            if (met == 2 || met == 3) {
                System.out.println(ex.getMessage());
                System.out.println("Для данного отрезка нельзя использовать этот метод");
                System.exit(0);
            }
        }
        switch (met) {
            case 2:
                Method_2 method_2 = new Method_2(a, b, e, equation, consol_or_file);
                method_2.do_it();
                Draw.draw(a,b,0,e,equation);
                break;
            case 3:
                Method_3 method_3 = new Method_3(a, b, e, equation, consol_or_file);
                method_3.do_it();
                Draw.draw(a,b,0,e,equation);
                break;
            case 5:
                Method_5 method_5 = new Method_5(a, b, e, equation, consol_or_file);
                method_5.do_it();
                Draw.draw(a,b,e,method_5.getLambda(),equation);
                break;
            default:
                System.out.println("Такого, увы, пока не умеем(");
        }
    }

    private static void data_is_norm(double a, double b, double e, int equation) throws InaccuracyException, NullRootException, MuchRootException, DerivativesExeption {
        int roots = 0;

        if (Math.abs(a - b) < e) {
            throw new InaccuracyException("Ну и зачем ты ввел такие данные, |a - b| < e!");
        }

        for (double i = a; i <= b - e; i = i + e) {
            if (Function.function(i, equation) >= 0 && Function.function(i + e, equation) <= 0 || Function.function(i, equation) <= 0 && Function.function(i + e, equation) >= 0) {
                roots++;
            }
        }

        if (roots > 1) {
            throw new MuchRootException("Ну и зачем ты ввел такие данные, тут явно больше 1 корня!");
        } else if (roots == 0) {
            throw new NullRootException("Ну и зачем ты ввел такие данные, корней нет!");
        }

        if (!((Function.derivative(a, equation) > 0 && Function.derivative(b, equation) > 0 && Function.derivative((a + b) / 2, equation) > 0) ||
                (Function.derivative(a, equation) < 0 && Function.derivative(b, equation) < 0 && Function.derivative((a + b) / 2, equation) < 0))) {
            throw new DerivativesExeption("Производная f'(x) не сохраняет знак на отрезке [a;b]");
        }
        if (!((Function.second_derivative(a, equation) >= 0 && Function.second_derivative(b, equation) >= 0 && Function.second_derivative((a + b) / 2, equation) >= 0)||
                (Function.second_derivative(a, equation) <= 0 && Function.second_derivative(b, equation) <= 0 && Function.second_derivative((a + b) / 2, equation) <= 0))) {
            throw new DerivativesExeption("Производная f''(x) не сохраняет знак на отрезке [a;b]");
        }
    }
}