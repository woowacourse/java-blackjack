package domain.participant;

import domain.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BetAmountTest {

    @ParameterizedTest
    @ValueSource(ints = {999, 0, -1000})
    @DisplayName("배팅 금액이 1000원 미만이면 예외가 발생한다.")
    void validBetAmount(int betAmount) {
        assertThatThrownBy(() -> new BetAmount(new BigDecimal(betAmount)))
                .isInstanceOf(DomainException.class);
    }

    @Test
    @DisplayName("블랙잭의 경우 배팅금액의 1.5배의 수익이 발생한다.")
    void calculateBlackjackPrize() {
        BetAmount betAmount = new BetAmount(new BigDecimal(1000));
        assertThat(betAmount.calculatePrize(new BigDecimal("1.5"))).isEqualTo(1500);
    }

    @Test
    @DisplayName("게임에서 질 경우 수익은 -배팅금액이 된다.")
    void calculateLosePrize() {
        BetAmount betAmount = new BetAmount(new BigDecimal(1000));
        assertThat(betAmount.calculatePrize(new BigDecimal(-1))).isEqualTo(-1000);
    }

    @Test
    @DisplayName("블랙잭이 아니고, 이기거나 비길 경우 배팅금액이 수익이 된다.")
    void calculateDrawOrWinPrize() {
        BetAmount betAmount = new BetAmount(new BigDecimal(1000));
        assertThat(betAmount.calculatePrize(new BigDecimal(1))).isEqualTo(1000);
    }
}
