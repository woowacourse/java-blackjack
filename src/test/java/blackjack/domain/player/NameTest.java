package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {
    @ParameterizedTest
    @ValueSource(strings = {"", "01234567890"})
    void 이름의_길이는_1이상_10이하여야_한다(String input) {
        // when & then
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Name(input));
        assertThat(e.getMessage()).isEqualTo("[ERROR] 이름의 길이는 1 이상, 10 이하여야 합니다.");
    }
}
