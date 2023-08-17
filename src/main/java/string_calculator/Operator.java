package string_calculator;

public enum Operator {
    PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/");

    private final String name;

    Operator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int operate(int num1, int num2) {
        switch (this.name) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            default:
                return num1 / num2;
        }
    }

    public static Operator fromName(String name) {
        for (Operator operator : Operator.values()) {
            if (operator.getName().equals(name)) {
                return operator;
            }
        }
        throw new IllegalArgumentException("해당 name에 매칭되는 값이 없습니다.");
    }
}
