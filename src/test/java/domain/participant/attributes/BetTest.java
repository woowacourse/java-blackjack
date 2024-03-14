package domain.participant.attributes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetTest {

    @DisplayName("베팅 금액을 더할 수 있다.")
    @Test
    void add() {
        Bet bet1 = new Bet(1000);
        Bet bet2 = new Bet(300);
        assertThat(bet1.add(bet2)).isEqualTo(new Bet(1300));
    }

    @DisplayName("베팅 금액을 뺄 수 있다.")
    @Test
    void subtract() {
        Bet bet1 = new Bet(1000);
        Bet bet2 = new Bet(300);
        assertThat(bet1.subtract(bet2)).isEqualTo(new Bet(700));
    }

    @DisplayName("베팅 금액을 곱할 수 있다.")
    @Test
    void multiply() {
        Bet bet1 = new Bet(1000);
        assertThat(bet1.multiply(1.5)).isEqualTo(new Bet(1500));
    }
}
