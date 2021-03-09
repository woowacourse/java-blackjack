package blackjack.domain.blackjackgame;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.player.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @DisplayName("돈을 추가한다.")
    @Test
    void testMoneyAdd() {
        Money money = new Money(1000);

        assertThat(money.add(new Money(500)).getValue()).isEqualTo(1500);
    }

    @DisplayName("블랙잭 게임을 블랙잭으로 승리할 경우 수익을 리턴한다.")
    @Test
    void testBlackjackWinProfit() {
        Money money = new Money(1000);
        assertThat(money.profit(GameResult.WIN, true).getValue()).isEqualTo(1500);
    }

    @DisplayName("블랙잭 게임을 승리할 경우 수익을 리턴한다.")
    @Test
    void testWinProfit() {
        Money money = new Money(1000);
        assertThat(money.profit(GameResult.WIN, false).getValue()).isEqualTo(1000);
    }

    @DisplayName("블랙잭 게임을 패배할 경우 수익을 리턴한다.")
    @Test
    void testLoseProfit() {
        Money money = new Money(1000);
        assertThat(money.profit(GameResult.LOSE, false).getValue()).isEqualTo(-1000);
    }

}