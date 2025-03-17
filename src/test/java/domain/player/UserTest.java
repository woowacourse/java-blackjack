package domain.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserTest {

    @Test
    void 딜러와_이름을_동일하게_설정한_경우_예외가_발생한다() {
        // given
        String name = Dealer.DEALER_NAME;

        // when & then
        Assertions.assertThatThrownBy(() -> User.of(name, 1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a"})
    void 이름의_길이가_2미만인_경우_예외가_발생한다(String name) {
        // when & then
        Assertions.assertThatThrownBy(() -> User.of(name, 1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345678901", "itsnotpossiblename"})
    void 이름의_길이가_10글자_초과인_경우_예외가_발생한다(String name) {
        // when & then
        Assertions.assertThatThrownBy(() -> User.of(name, 1000))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
