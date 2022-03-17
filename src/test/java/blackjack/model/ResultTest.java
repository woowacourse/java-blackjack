package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @DisplayName("15000원을 배팅해서 Blackjack이 나오면 배팅금의 1.5배인 22500원을 얻는다")
    @Test
    void apply_blackjack_10000() {
        final int amount = 15000;
        Money returnMoney = Result.BLACKJACK.apply(new Money(amount));
        final int expected = (int) Math.round(amount * 1.5);

        assertThat(returnMoney.getAmount()).isEqualTo(expected);
    }

    @DisplayName("20000원을 배팅해서 패배하면 배팅금 20000원을 잃는다")
    @Test
    void apply_lose_20000() {
        final int amount = 20000;
        Money returnMoney = Result.LOSE.apply(new Money(amount));

        assertThat(returnMoney.getAmount()).isEqualTo(amount * -1);
    }

    @DisplayName("30000원을 배팅해서 승리하면 배팅금 30000원을 얻는다")
    @Test
    void apply_win_30000() {
        final int amount = 30000;
        Money returnMoney = Result.WIN.apply(new Money(amount));

        assertThat(returnMoney.getAmount()).isEqualTo(amount);
    }

    @DisplayName("10000원을 배팅해서 무승부이면 수익도 손해도 없다")
    @Test
    void apply_tie_10000() {
        Money returnMoney = Result.TIE.apply(new Money(10000));

        assertThat(returnMoney.getAmount()).isEqualTo(0);
    }
}
