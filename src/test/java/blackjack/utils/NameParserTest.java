package blackjack.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NameParserTest {
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("이름이 입력이 아예 안됐을 떄")
    void nullInputTest(String input) {
        assertThatThrownBy(() -> NameParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1개 이상");
    }
}
