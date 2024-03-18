package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfitAmountTest {

    @DisplayName("수익은 더할 수 있다")
    @Test
    public void add() {
        ProfitAmount first = new ProfitAmount(0);
        ProfitAmount second = new ProfitAmount(1);

        assertThat(first.add(second).getAmount()).isEqualTo(1);
    }

    @DisplayName("수익은 가지고 있는 값에 음수를 붙여 반환할 수 있다")
    @Test
    public void inverse() {
        ProfitAmount profitAmount = new ProfitAmount(1000);

        assertThat(profitAmount.inverse().getAmount()).isEqualTo(-1000);
    }
}
