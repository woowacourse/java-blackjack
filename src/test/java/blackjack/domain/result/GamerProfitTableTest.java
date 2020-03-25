package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GamerProfitTableTest {

    @Test
    void gamerProfit() {
        Dealer dealer = new Dealer();
        Profit dealerProfit = Profit.from(0);
        Player pobi = new Player("pobi");
        Profit pobiProfit = Profit.from(-10000);
        dealerProfit = dealerProfit.minus(pobiProfit);
        Player jay = new Player("jay");
        Profit jayProfit = Profit.from(15000);
        dealerProfit = dealerProfit.minus(jayProfit);
        Player elly = new Player("elly");
        Profit ellyProfit = Profit.from(20000);
        dealerProfit = dealerProfit.minus(ellyProfit);

        Map<Gamer, Profit> gamerProfits = new LinkedHashMap<>();
        gamerProfits.put(dealer, dealerProfit);
        gamerProfits.put(pobi, pobiProfit);
        gamerProfits.put(jay, jayProfit);
        gamerProfits.put(elly, ellyProfit);

        GamerProfitTable gamerProfitTable = GamerProfitTable.from(gamerProfits);
        assertThat(gamerProfitTable.getGamerProfits())
                .containsEntry(dealer, dealerProfit)
                .containsEntry(pobi, pobiProfit)
                .containsEntry(jay, jayProfit)
                .containsEntry(elly, ellyProfit);
    }
}