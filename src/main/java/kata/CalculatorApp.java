package kata;


import java.util.Arrays;

public class CalculatorApp {

    public static void main(String[] arg) {
        /*
        for (String s : Arrays.asList("II - II", "1-3", "2.1-2", "15-0", "2/0", "1 + 2",
                "VI / III", "I - II", "I + 1", "1", "1 + 2 + 3")) {
            try {
                System.out.println("Input: " + s);
                System.out.println("Output:" + new Calculator(s).calc());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        if (arg.length > 0) {
            for (String s : arg) {
                var calculator = new Calculator(s);
                System.out.println(calculator.calc());
            }
        }
    }
}
