package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("이름")
class NameTest {

    @DisplayName("부적절한 이름 시, 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"9878766", " ", "    ", "@#@asf$#@$#@", "딜러"})
    void invalidNameFormat(final String name) {
        //given & when & then
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
