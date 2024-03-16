package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Profit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfitTest {

    @DisplayName("수익은 더할 수 있다")
    @Test
    public void add() {
        Profit first = Profit.from(0);
        Profit second = Profit.from(1);

        assertThat(first.add(second).getValue()).isEqualTo(1);
    }

    @DisplayName("수익은 가지고 있는 값에 음수를 붙여 반환할 수 있다")
    @Test
    public void inverse() {
        Profit profit = Profit.from(1000);

        assertThat(profit.inverse().getValue()).isEqualTo(-1000);
    }
}
