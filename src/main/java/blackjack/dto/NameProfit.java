package blackjack.dto;

import blackjack.model.money.Profit;
import blackjack.model.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record NameProfit(String name, int profit) {

    public static List<NameProfit> createNameProfits(final Map<Player, Profit> playerProfit) {
        final List<NameProfit> nameProfits = new ArrayList<>();
        for (Player player : playerProfit.keySet()) {
            final int profit = playerProfit.get(player).getProfit();
            nameProfits.add(new NameProfit(player.getName(), profit));
        }
        return nameProfits;
    }
}
