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
                if (num2 == 0) {
                    throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
                }
                return num1 / num2;
        }
    }

    public static Operator fromName(String name) {
        for (Operator operator : Operator.values()) {
            if (operator.getName().equals(name)) {
                return operator;
            }
        }
        throw new IllegalArgumentException("잘못된 연산자가 사용되었습니다.");
    }
}
