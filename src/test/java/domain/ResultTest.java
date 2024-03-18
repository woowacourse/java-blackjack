package domain;

import static domain.BetAmountFixture.betAmount10_000;
import static domain.HandsTestFixture.blackJack;
import static domain.HandsTestFixture.bustHands;
import static domain.HandsTestFixture.sum17Size3One;
import static domain.HandsTestFixture.sum18Size2;
import static domain.HandsTestFixture.sum20Size2;
import static domain.HandsTestFixture.sum20Size3;
import static domain.ProfitFixture.loseProfit10_000;
import static domain.ProfitFixture.profit0;
import static domain.ProfitFixture.profit10_000;
import static domain.ProfitFixture.profit15_000;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @DisplayName("WIN_BLACKJACK이면 배팅 금액의 1.5배의 수익을 반환한다.")
    @Test
    void isWIN_BLACKJACK() {
        // given && when
        final Profit result = Result.calculateProfit(blackJack, bustHands, betAmount10_000);

        // then
        Assertions.assertThat(result).isEqualTo(profit15_000);
    }

    @DisplayName("WIN이면 1배의 배팅 금액의 수익을 반환한다.")
    @Test
    void isWIN() {
        // given && when
        final Profit result = Result.calculateProfit(sum20Size2, sum18Size2, betAmount10_000);

        // then
        Assertions.assertThat(result).isEqualTo(profit10_000);
    }

    @DisplayName("LOSE이면 배팅 금액만큼을 잃는다.")
    @Test
    void isLOSE() {
        // given && when
        final Profit result = Result.calculateProfit(sum17Size3One, sum20Size3, betAmount10_000);

        // then
        Assertions.assertThat(result).isEqualTo(loseProfit10_000);
    }

    @DisplayName("TIE이면 수익을 0으로 만든다.")
    @Test
    void isTIE() {
        // given && when
        final Profit result = Result.calculateProfit(sum20Size2, sum20Size2, betAmount10_000);

        // then
        Assertions.assertThat(result).isEqualTo(profit0);
    }
}
