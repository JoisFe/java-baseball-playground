package string_calculator;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

class StringCalculatorApplicationTest {

    @Test
    void main() {
        // given
        String value = "2 + 3 - 1 * 4 / 2\n";
        String expectedResult = "8\n";

        InputStream inputStream = System.in;
        System.setIn(new ByteArrayInputStream(value.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = System.out;
        System.setOut(new PrintStream(outputStream));

        // when
        StringCalculatorApplication.main(new String[0]);
        System.setIn(inputStream);
        System.setOut(printStream);

        String result = outputStream.toString();

        // then
        assertThat(result).isEqualTo(expectedResult);
    }


}
