package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Name;
import blackjack.domain.participant.Profit;
import blackjack.domain.participant.ProfitDetails;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfitDetailsTest {

    @DisplayName("모든 플레이어의 수익금을 더한만큼의 음수를 딜러 수익금으로 계산한다")
    @Test
    public void calculateDealerProfit() {
        ProfitDetails profitDetails = new ProfitDetails(Map.of(new Name("이상"), Profit.from(1000)));

        assertThat(profitDetails.calculateDealerProfit().getValue()).isEqualTo(-1000);
    }
}
