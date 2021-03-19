package blakcjack.domain.money;

import blakcjack.domain.outcome.Outcome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BettingMoneyTest {
    @DisplayName("객체 생성 성공")
    @Test
    void create() {
        final BettingMoney bettingMoney = new BettingMoney(10000);
        assertThat(bettingMoney).isEqualTo(new BettingMoney(10000));
    }

    @DisplayName("블랙잭으로 승리한 경우 베팅 머니의 1.5배 수익을 얻는다.")
    @Test
    void toEarning_win_blackjack() {
        final BettingMoney bettingMoney = new BettingMoney(10000);
        final int earning = bettingMoney.toEarning(Outcome.WIN, true);
        assertThat(earning).isEqualTo(15000);
    }

    @DisplayName("점수로 승리한 경우 베팅 머니의 1배 수익을 얻는다.")
    @Test
    void toEarning_win() {
        final BettingMoney bettingMoney = new BettingMoney(10000);
        final int earning = bettingMoney.toEarning(Outcome.WIN, false);
        assertThat(earning).isEqualTo(10000);
    }

    @DisplayName("비긴 경우 수익이 0이다.")
    @Test
    void toEarning_draw() {
        final BettingMoney bettingMoney = new BettingMoney(10000);
        final int earning = bettingMoney.toEarning(Outcome.DRAW, false);
        assertThat(earning).isEqualTo(0);
    }

    @DisplayName("패배한 경우 베팅 머니 만큼 -수익을 얻는다.")
    @Test
    void toEarning_lose() {
        final BettingMoney bettingMoney = new BettingMoney(10000);
        final int earning = bettingMoney.toEarning(Outcome.LOSE, false);
        assertThat(earning).isEqualTo(-10000);
    }
}
