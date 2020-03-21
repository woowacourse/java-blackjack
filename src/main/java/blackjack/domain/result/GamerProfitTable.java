package blackjack.domain.result;

import blackjack.domain.gamer.Gamer;

import java.util.Map;

public class GamerProfitTable {

    private Map<Gamer, Profit> gamerProfits;

    private GamerProfitTable(Map<Gamer, Profit> gamerProfits) {
        this.gamerProfits = gamerProfits;
    }

    public static GamerProfitTable from(Map<Gamer, Profit> gamerProfits) {
        return new GamerProfitTable(gamerProfits);
    }

    public Map<Gamer, Profit> getGamerProfits() {
        return gamerProfits;
    }
}
