package kata;

import kata.utils.StringUtils;

import java.util.Optional;

public class Calculator {

    private final String source;
    private String result;

    public Calculator(String source) {
        //Лишние пробелы нам ни к чему
        this.source = source.replaceAll(" ", "");
    }

    public String calc() {
        Optional<Operation> operationOptional = Operation.getOperation(source);
        if (operationOptional.isEmpty()) {
            throw new IllegalStateException("строка не является математической операцией");
        }
        operationOptional.ifPresent(operation -> {
            String name = operation.getName();
            var first = StringUtils.getStringBefore(source, name);
            var second = StringUtils.getStringAfter(source, name);
            if (StringUtils.isEmpty(first) ||
                    Operation.getOperation(second).isPresent()) {
                wrongFormat();
            }
            calc(first, second, operation);
        });
        return result;
    }



    private void calc(String first, String second, Operation operation) {
        var operandFormatter = getOperandFormatter(first, second);
        var firstInt = operandFormatter.fromString(first);
        var secondInt = operandFormatter.fromString(second);
        check1To10(firstInt, secondInt);
        int value = operation.calc(firstInt, secondInt);
        result = operandFormatter.toString(value);
    }

    private OperandFormatter getOperandFormatter(String first, String second) {
        Optional<OperandFormatter> style = OperandFormatter.getFormatter(first);
        if (style.isEmpty()) {
            throw new IllegalStateException("Неверный формат входного числа");
        }
        var operandFormatter = style.get();
        OperandFormatter.getFormatter(second).ifPresent(secondStyle -> {
            if (operandFormatter != secondStyle) {
                throw new IllegalStateException("используются одновременно разные системы счисления");
            }
        });
        return operandFormatter;
    }

    private void wrongFormat() {
        throw new IllegalStateException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
    }

    private void check1To10(int... values) {
        for (int value : values) {
            if (value < 1 || value > 10) {
                throw new IllegalStateException("Число больше 10 или меньше 1");
            }
        }
    }

}
