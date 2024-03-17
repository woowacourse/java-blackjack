package blackjack.model.blackjackgame;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {

    @DisplayName("수익률을 합산한다.")
    @Test
    void sum() {
        Profit profit = new Profit(2);
        Profit other = new Profit(5);
        Profit result = profit.sum(other);

        assertThat(result).isEqualTo(new Profit(7));
    }

    @DisplayName("수익률 부호를 뒤집는다.")
    @Test
    void reverse() {
        int given = 10;
        int expected = -given;
        Profit profit = new Profit(given);

        Profit result = profit.reverse();

        assertThat(result).isEqualTo(new Profit(expected));
    }
}
