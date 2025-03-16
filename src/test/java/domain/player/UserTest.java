package domain.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void 딜러와_이름을_동일하게_설정한_경우_예외가_발생한다() {
        // given
        String name = Dealer.DEALER_NAME;

        // when & then
        Assertions.assertThatThrownBy(() -> User.of(name, 1000))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
