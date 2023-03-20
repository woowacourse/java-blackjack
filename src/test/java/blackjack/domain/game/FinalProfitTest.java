package blackjack.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.participant.Player;

class FinalProfitTest {

    @DisplayName("Player의 전체 수익과 Dealer의 수익의 합은 0이다.")
    @Test
    void calculateDealerProfitBySumOfPlayerProfit() {
        // given
        String pobiMoney = "10000";
        String crongMoney = "20000";

        Map<Player, Money> playerProfit = new LinkedHashMap<>();
        playerProfit.put(new Player("pobi"), new Money(pobiMoney));
        playerProfit.put(new Player("crong"), new Money(crongMoney));
        FinalProfit finalProfit = new FinalProfit(playerProfit);

        // when
        int dealerProfit = finalProfit.calculateDealerProfit();

        // then
        assertThat(dealerProfit).isEqualTo((Integer.parseInt(pobiMoney) + Integer.parseInt(crongMoney)) * -1);
    }
}
