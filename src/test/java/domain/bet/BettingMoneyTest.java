package domain.bet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.game.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingMoneyTest {

    @Test
    void 베팅_금액을_생성한다() {
        BettingMoney money = new BettingMoney(1000);
        assertThat(money.getAmount()).isEqualTo(1000);
    }

    @Test
    void 양의_정수가_아니라면_예외가_발생한다() {
        assertThatThrownBy(() -> new BettingMoney(-1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("승리하면 베팅 금액만큼 수익이다")
    @Test
    void 승리하면_베팅_금액만큼_수익이다() {
        BettingMoney money = new BettingMoney(10000);
        assertThat(money.calculateProfit(Result.WIN)).isEqualTo(10000);
    }

    @DisplayName("블랙잭 승리하면 1.5배 수익이다")
    @Test
    void 블랙잭_승리하면_1점5배_수익이다() {
        BettingMoney money = new BettingMoney(10000);
        assertThat(money.calculateProfit(Result.BLACKJACK_WIN)).isEqualTo(15000);
    }

    @DisplayName("패배하면 베팅 금액만큼 손해다")
    @Test
    void 패배하면_베팅_금액만큼_손해다() {
        BettingMoney money = new BettingMoney(10000);
        assertThat(money.calculateProfit(Result.LOSE)).isEqualTo(-10000);
    }

    @DisplayName("무승부면 수익이 0이다")
    @Test
    void 무승부면_수익이_0이다() {
        BettingMoney money = new BettingMoney(10000);
        assertThat(money.calculateProfit(Result.TIE)).isEqualTo(0);
    }
}
