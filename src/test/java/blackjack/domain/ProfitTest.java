package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfitTest {

    @DisplayName("승리할 경우 배팅 금액만큼 양의 수익금으로 갖는다")
    @Test
    public void fromWin() {
        Profit profit = Profit.fromWin(new Money(1000));

        assertThat(profit.getValue()).isEqualTo(1000);
    }

    @DisplayName("패배할 경우 배팅 금액만큼 음의 수익금으로 갖는다")
    @Test
    public void fromLose() {
        Profit profit = Profit.fromLose(new Money(1000));

        assertThat(profit.getValue()).isEqualTo(-1000);
    }
}
