package kata;

import kata.roman.RomanConverter;

import java.util.Optional;

public enum OperandFormatter {

    arabic{
        @Override
        public boolean isStyle(String value) {
            try {
                fromString(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        @Override
        public int fromString(String first) {
            return Integer.parseInt(first);
        }

        @Override
        public String toString(int value) {
            return Integer.toString(value);
        }
    }, roman {
        @Override
        public boolean isStyle(String value) {
            try {
                RomanConverter.romanToArabic(value);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }

        @Override
        public int fromString(String first) {
            return RomanConverter.romanToArabic(first);
        }

        @Override
        public String toString(int value) {
            if (value < 1) {
                throw new IllegalStateException("в римской системе нет отрицательных чисел");
            }
            return RomanConverter.arabicToRoman(value);
        }
    };

    abstract public boolean isStyle(String value);


    public static Optional<OperandFormatter> getFormatter(String s) {
        for (OperandFormatter value : OperandFormatter.values()) {
            if (value.isStyle(s)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }

    public abstract int fromString(String first);

    public abstract String toString(int value);
}
