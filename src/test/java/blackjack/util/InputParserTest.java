package blackjack.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputParserTest {

    @Test
    @DisplayName("이름을 쉼표로 구분하여 리스트로 반환한다.")
    void parseStringToList() {
        String input = "hula,sana,jason";
        List<String> result = InputParser.parseStringToList(input);

        assertAll(() -> {
            assertThat(result.get(0)).isEqualTo("hula");
            assertThat(result.get(1)).isEqualTo("sana");
            assertThat(result.get(2)).isEqualTo("jason");
        });
    }
}
