package string_calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StringCalculatorApplicationTest {

    @ParameterizedTest
    @ValueSource(strings = {"2 + 3 - 1 * 4 / 2\n"})
    @DisplayName("문자열 수식에 대한 정확한 해답을 출력하는지 테스트")
    void stringCalculatorTest(String value) {
        // given
        String expectedResult = "8\n";

        System.setIn(new ByteArrayInputStream(value.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // when
        StringCalculatorApplication.main(new String[0]);

        InputStream inputStream = System.in;
        System.setIn(inputStream);
        PrintStream printStream = System.out;
        System.setOut(printStream);

        String result = outputStream.toString();

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @ValueSource(strings = "2 ? 3 - 1 * 4 / 2\n")
    @DisplayName("올바르지 않은 연산자가 입력된 경우 테스트")
    void invalidOperatorTest(String value) {
        // given
        System.setIn(new ByteArrayInputStream(value.getBytes()));

        // when then
        assertThatThrownBy(() -> StringCalculatorApplication.main(new String[0]))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("잘못된 연산자가 사용되었습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = "a + 3 - 1 * 4 / 2\n")
    @DisplayName("올바르지 않은 피연산자가 입력된 경우 테스트")
    void invalidOperandTest(String value) {
        // given
        System.setIn(new ByteArrayInputStream(value.getBytes()));

        // when then
        assertThatThrownBy(() -> StringCalculatorApplication.main(new String[0]))
            .isInstanceOf(NumberFormatException.class)
            .hasMessage("잘못된 피연산자가 사용되었습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = "3 / 0 - 1 * 4 / 2\n")
    @DisplayName("0 으로 나누는 수식이 입력된 경우 테스트")
    void zeroDivideTest(String value) {
        // given
        System.setIn(new ByteArrayInputStream(value.getBytes()));

        // when then
        assertThatThrownBy(() -> StringCalculatorApplication.main(new String[0]))
            .isInstanceOf(NumberFormatException.class)
            .hasMessage("0으로 나눌 수 없습니다.");
    }
}
