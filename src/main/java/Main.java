import java.io.*;
import java.util.Scanner;

public class Main {
    private static double a, b, e;
    private static int met;
    private static final String path_to_file = "src/main/resources/input_1";

    public static void main(String[] args) throws IOException {
        Scanner inConsole = new Scanner(System.in);

        System.out.println("Введите \"file\" для ввода с файла \n" +
                "               или               \n" +
                " \"console\" для ввода с консоли  \n");
        String consol_or_file = inConsole.next();

        while (!consol_or_file.equals("console") && !consol_or_file.equals("file")) {
            System.out.println("Ну я же попросил!");
            consol_or_file = inConsole.next();
        }

        if (consol_or_file.equals("console")) {
            //TODO ввод с консоли
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
                met = Integer.parseInt(reader.readLine().trim());
                consol_or_file = reader.readLine().trim();
                if (!consol_or_file.equals("console") && !consol_or_file.equals("file")) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ignored) {
                System.out.println("В файле кривые данные,поменяйте их и попробуйде снова");
            }

            if (data_is_norm(a,b,e)) {
                switch (met){
                    case 2:
                        //вызываем функцию
                        break;
                    case 3:
                        //вызываем функцию
                        break;
                    case 5:
                        //вызываем функцию
                        break;
                    default:
                        System.out.println("Такого, увы, пока не умеем(");
                }
            }
        }
    }

    private static boolean data_is_norm(double a, double b, double e) {
        boolean flag = true;
        //TODO проверка что а-b>e , что там есть корни и их количество и т.д
        return flag;
    }
}
// это значение левой границы - а
// это значение правой границы - b
// это значение погрешности - е
// это номер используемого метода
// это запись в файл или нет