package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.game.Outcome;
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
        assertThat(money.calculateProfit(Outcome.WIN)).isEqualTo(10000);
    }

    @DisplayName("블랙잭 승리하면 1.5배 수익이다")
    @Test
    void 블랙잭_승리하면_1점5배_수익이다() {
        BettingMoney money = new BettingMoney(10000);
        assertThat(money.calculateProfit(Outcome.BLACKJACK_WIN)).isEqualTo(15000);
    }

    @DisplayName("패배하면 베팅 금액만큼 손해다")
    @Test
    void 패배하면_베팅_금액만큼_손해다() {
        BettingMoney money = new BettingMoney(10000);
        assertThat(money.calculateProfit(Outcome.LOSE)).isEqualTo(-10000);
    }

    @DisplayName("무승부면 수익이 0이다")
    @Test
    void 무승부면_수익이_0이다() {
        BettingMoney money = new BettingMoney(10000);
        assertThat(money.calculateProfit(Outcome.TIE)).isEqualTo(0);
    }

    @DisplayName("베팅 금액이 0이면 예외가 발생한다")
    @Test
    void 베팅_금액이_0이면_예외가_발생한다() {
        assertThatThrownBy(() -> new BettingMoney(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("홀수 금액의 블랙잭 승리는 소수점을 버린다")
    @Test
    void 홀수_금액의_블랙잭_승리는_소수점을_버린다() {
        BettingMoney money = new BettingMoney(999);
        assertThat(money.calculateProfit(Outcome.BLACKJACK_WIN)).isEqualTo(1498);
    }
}
