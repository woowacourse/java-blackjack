package domain.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BetAmountTest {

    @Test
    void BET_AMOUNT가_0이면_예외가_발생한다() {
        assertThatThrownBy(() -> new BetAmount(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void BET_AMOUNT가_0보다_작으면_예외가_발생한다() {
        assertThatThrownBy(() -> new BetAmount(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
