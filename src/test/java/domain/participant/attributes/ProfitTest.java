package domain.participant.attributes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {

    @DisplayName("수익을 더할 수 있다.")
    @Test
    void add() {
        Profit profit1 = new Profit(1000);
        Profit profit2 = new Profit(300);
        assertThat(profit1.add(profit2)).isEqualTo(new Profit(1300));
    }

    @DisplayName("수익을 뺄 수 있다.")
    @Test
    void subtract() {
        Profit profit1 = new Profit(1000);
        Profit profit2 = new Profit(300);
        assertThat(profit1.subtract(profit2)).isEqualTo(new Profit(700));
    }

    @DisplayName("수익을 곱할 수 있다.")
    @Test
    void multiply() {
        Profit profit1 = new Profit(1000);
        assertThat(profit1.multiply(1.5)).isEqualTo(new Profit(1500));
    }
}
