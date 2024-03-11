package domain;

import domain.score.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetAmountTest {

    @Test
    @DisplayName("승리 시 배팅금액 만큼 수익을 얻는다.")
    void determineProfit_Win() {
        BetAmount betAmount = new BetAmount(1000);

        Assertions.assertThat(betAmount.determineProfit(Status.WIN)).isEqualTo(1000);
    }

    @Test
    @DisplayName("무승부 시 수익을 얻지 못한다.")
    void determineProfit_Tie() {
        BetAmount betAmount = new BetAmount(1000);

        Assertions.assertThat(betAmount.determineProfit(Status.TIE)).isEqualTo(0);
    }

    @Test
    @DisplayName("패배 시 배팅금액 만큼 금액을 잃는다.")
    void determineProfit_Lose() {
        BetAmount betAmount = new BetAmount(1000);

        Assertions.assertThat(betAmount.determineProfit(Status.LOSE)).isEqualTo(-1000);
    }
}
