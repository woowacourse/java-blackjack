package blackjack.resultstate;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.money.BetMoney;
import blackjack.money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchResultTest {

    BetMoney money = BetMoney.of(10_000);

    @Test
    @DisplayName("블랙잭 승리할 때의 이익을 계산한다.")
    void calculateProfitOnBlackJack() {
        // when
        Money profit = MatchResult.PARTICIPANT_BLACKJACK_WIN.calculateProfit(money);
        // then
        assertThat(profit).isEqualTo(Money.of(15_000));
    }

    @Test
    @DisplayName("일반 승리할 때의 이익을 계산한다.")
    void calculateProfitOnNormalWin() {
        // when
        Money profit = MatchResult.PARTICIPANT_WIN.calculateProfit(money);
        // then
        assertThat(profit).isEqualTo(Money.of(10_000));
    }

    @Test
    @DisplayName("무승부일 때의 이익을 계산한다.")
    void calculateProfitOnTie() {
        // when
        Money profit = MatchResult.TIE.calculateProfit(money);
        // then
        assertThat(profit).isEqualTo(Money.of(0));
    }

    @Test
    @DisplayName("패배할 때의 이익을 계산한다.")
    void calculateProfitOnLose() {
        // when
        Money profit = MatchResult.DEALER_WIN.calculateProfit(money);
        // then
        assertThat(profit).isEqualTo(Money.of(-10_000));
    }
}
