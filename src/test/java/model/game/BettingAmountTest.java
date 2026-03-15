package model.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import model.game.exception.BettingUnitMismatchException;
import model.game.exception.UnderMinimumAmountException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BettingAmountTest {
    @ParameterizedTest
    @CsvSource(value = {
            "1000,1000",
            "1100,1100"
    })
    void 베팅_기준을_만족하면_성공적으로_객체를_생성한다(int validAmount, int expected) {
        BettingAmount bettingAmount = BettingAmount.from(validAmount);

        assertThat(bettingAmount.getAmount()).isEqualByComparingTo(new BigDecimal(expected));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1000, 900, 990})
    void 베팅_최소_금액보다_작으면_예외를_발생한다(int invalidAmount) {
        assertThatThrownBy(() -> BettingAmount.from(invalidAmount))
                .isInstanceOf(UnderMinimumAmountException.class);
    }

    @Test
    void 베팅_금액의_단위가_맞지_않으면_예외를_발생한다() {
        assertThatThrownBy(() -> BettingAmount.from(1050))
                .isInstanceOf(BettingUnitMismatchException.class);
    }
}
