package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.result.BlackJack;
import blackjack.domain.result.Draw;
import blackjack.domain.result.Keep;
import blackjack.domain.result.Lose;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetTest {

    @Test
    @DisplayName("입력 금액이 0원 이하일 경우 예외를 발생시킨다.")
    void createGamerExceptionMoneyEqualStandard() {
        assertThatThrownBy(() -> new Bet(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅은 1원부터 가능합니다.");
    }

    @Test
    @DisplayName("블랙잭 결과는 수익을 1.5배로 계산한다.")
    void calculateCurrentAmountBlackJack() {
        Bet bet = new Bet(1000);
        bet.calculateBenefit(new BlackJack());
        assertThat(bet.getAmount()).isEqualTo(1500);
    }

    @Test
    @DisplayName("Keep 결과는 수익을 1배로 계산한다.")
    void calculateCurrentAmountKeep() {
        Bet bet = new Bet(1000);
        bet.calculateBenefit(new Keep());
        assertThat(bet.getAmount()).isEqualTo(1000);
    }

    @Test
    @DisplayName("Draw 결과는 수익을 0으로 계산한다.")
    void calculateCurrentAmountDraw() {
        Bet bet = new Bet(1000);
        bet.calculateBenefit(new Draw());
        assertThat(bet.getAmount()).isEqualTo(0);
    }

    @Test
    @DisplayName("Lose 결과는 수익을 -amount 로 계산한다.")
    void calculateCurrentAmountLose() {
        Bet bet = new Bet(1000);
        bet.calculateBenefit(new Lose());
        assertThat(bet.getAmount()).isEqualTo(-1000);
    }
}
