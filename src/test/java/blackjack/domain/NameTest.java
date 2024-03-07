package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {
    @ParameterizedTest
    @ValueSource(strings = {"", "01234567890"})
    void 이름의_길이는_1이상_10이하여야_한다(String value) {
        Assertions.assertThatCode(() -> new Name(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
