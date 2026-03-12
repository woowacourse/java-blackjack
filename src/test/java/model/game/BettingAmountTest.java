package model.game;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import model.game.exception.BettingUnitMismatchException;
import model.game.exception.UnderMinimumAmountException;
import org.junit.jupiter.api.Test;

class BettingAmountTest {
    @Test
    void 베팅_최소_금액보다_작으면_예외를_발생한다() {
        assertThatThrownBy(() -> BettingAmount.from(500))
                .isInstanceOf(UnderMinimumAmountException.class);
    }

    @Test
    void 베팅_금액의_단위가_맞지_않으면_예외를_발생한다() {
        assertThatThrownBy(() -> BettingAmount.from(1150))
                .isInstanceOf(BettingUnitMismatchException.class);
    }
}