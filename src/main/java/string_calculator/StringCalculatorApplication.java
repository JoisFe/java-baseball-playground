package string_calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 단위 테스트 실습 - 문자열 계산기 다음 요구사항을 JUnit을 활용해 단위 테스트 코드를 추가해 구현한다. 요구사항 사용자가 입력한 문자열 값에 따라 사칙연산을 수행할 수 있는 계산기를 구현해야 한다. 문자열
 * 계산기는 사칙연산의 계산 우선순위가 아닌 입력 값에 따라 계산 순서가 결정된다. 즉, 수학에서는 곱셈, 나눗셈이 덧셈, 뺄셈 보다 먼저 계산해야 하지만 이를 무시한다. 예를 들어 "2 + 3 * 4 / 2"와
 * 같은 문자열을 입력할 경우 2 + 3 * 4 / 2 실행 결과인 10을 출력해야 한다.
 */
public class StringCalculatorApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        String[] values = value.split(" ");

        List<Integer> nums = new ArrayList<>();
        List<Operator> calOperator = new ArrayList<>();


        for (int i = 0; i < values.length; ++i) {
            if (i % 2 == 0) {
                try {
                    nums.add(Integer.parseInt(values[i]));
                    continue;
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("잘못된 피연산자가 사용되었습니다.");
                }
            }

            calOperator.add(Operator.fromName(values[i]));
        }

        for (int i = 1; i < nums.size(); ++i) {
            nums.set(i, calOperator.get(i - 1).operate(nums.get(i - 1), nums.get(i)));
        }

        System.out.println(nums.get(nums.size() - 1));
     }
}
