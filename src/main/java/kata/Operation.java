package kata;

import java.util.Optional;

public enum Operation {

    multiply("*") {
        public int calc(int x, int y) {
            return x * y;
        }
    }, divide("/") {
        public int calc(int x, int y) {
            return x / y;
        }
    }, plus ("+"){
        public int calc(int x, int y) {
            return x + y;
        }
    }, minus ("-"){
        public int calc(int x, int y) {
            return x - y;
        }
    };

    private String name;

    Operation(String name) {
        this.name = name;
    }

    abstract public int calc(int x, int y);

    public String getName() {
        return name;
    }

    public static Optional<Operation> getOperation(String source) {

        for (Operation value : Operation.values()) {
            if (source.contains(value.getName())) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}
