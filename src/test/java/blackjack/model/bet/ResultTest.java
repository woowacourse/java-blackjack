package blackjack.model.bet;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @DisplayName("15000원을 배팅해서 Blackjack이 나오면 배팅금의 1.5배인 22500원을 얻는다")
    @Test
    void apply_blackjack_10000() {
        final int amount = 15000;
        Amount profit = Result.BLACKJACK.apply(new Amount(amount));
        final int expected = (int) Math.round(amount * 1.5);

        assertThat(profit.getValue()).isEqualTo(expected);
    }

    @DisplayName("20000원을 배팅해서 패배하면 배팅금 20000원을 잃는다")
    @Test
    void apply_lose_20000() {
        final int amount = 20000;
        Amount profit = Result.LOSE.apply(new Amount(amount));

        assertThat(profit.getValue()).isEqualTo(amount * -1);
    }

    @DisplayName("30000원을 배팅해서 승리하면 배팅금 30000원을 얻는다")
    @Test
    void apply_win_30000() {
        final int amount = 30000;
        Amount profit = Result.WIN.apply(new Amount(amount));

        assertThat(profit.getValue()).isEqualTo(amount);
    }

    @DisplayName("10000원을 배팅해서 무승부이면 수익도 손해도 없다")
    @Test
    void apply_tie_10000() {
        Amount profit = Result.TIE.apply(new Amount(10000));

        assertThat(profit.getValue()).isEqualTo(0);
    }
}
