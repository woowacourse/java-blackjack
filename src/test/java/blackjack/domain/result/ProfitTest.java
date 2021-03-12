package blackjack.domain.result;

import blackjack.domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfitTest {
    @DisplayName("Profit 객체를 생성한다.")
    @Test
    public void createProfit() {
        Profit profit = new Profit(Arrays.asList(
                new Player("amazzi", 10000),
                new Player("dani", 50000)));

        assertThat(profit).isInstanceOf(Profit.class);
    }
}
