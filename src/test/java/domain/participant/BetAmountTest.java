package domain.participant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BetAmountTest {

    @ParameterizedTest
    @ValueSource(ints = {999, 0, -1000})
    void validBetAmount(int betAmount) {
        assertThatThrownBy(() -> new BetAmount(new BigDecimal(betAmount)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void calculateBlackjackPrize() {
        BetAmount betAmount = new BetAmount(new BigDecimal(1000));
        assertThat(betAmount.calculatePrize(new BigDecimal("1.5"))).isEqualTo(1500);
    }

    @Test
    void calculateLosePrize() {
        BetAmount betAmount = new BetAmount(new BigDecimal(1000));
        assertThat(betAmount.calculatePrize(new BigDecimal(-1))).isEqualTo(-1000);
    }

    @Test
    void calculateDrawOrWinPrize() {
        BetAmount betAmount = new BetAmount(new BigDecimal(1000));
        assertThat(betAmount.calculatePrize(new BigDecimal(1))).isEqualTo(1000);
    }
}
