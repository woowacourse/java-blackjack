package blackjack.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.money.BetMoney;
import blackjack.money.PlayerBet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchResultsTest {

    @Test
    @DisplayName("이름이 존재하지 않는 경우 예외를 발생시킨다.")
    void nameNotFoundTest() {
        // given
        PlayerBet bet = new PlayerBet("aru", BetMoney.of(1_000));
        MatchResults matchResults = new MatchResults();
        // when, then
        assertThatThrownBy(() -> matchResults.calculateProfitByBet(bet))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 이름입니다.");
    }

    @Test
    @DisplayName("각 승패 상태에 따른 이득을 계산한다.")
    void calculateProfitTest() {
        // given
        int amount = 10_000;
        int tieProfit = MatchResult.TIE.calculateProfit(amount);
        int loseProfit = MatchResult.LOSE.calculateProfit(amount);
        int normalWinProfit = MatchResult.NORMAL_WIN.calculateProfit(amount);
        int blackjackWinProfit = MatchResult.BLACKJACK_WIN.calculateProfit(amount);
        // when, then
        assertAll(
                () -> assertThat(tieProfit).isEqualTo(0),
                () -> assertThat(loseProfit).isEqualTo(-10_000),
                () -> assertThat(normalWinProfit).isEqualTo(10_000),
                () -> assertThat(blackjackWinProfit).isEqualTo(15_000)
        );
    }
}
