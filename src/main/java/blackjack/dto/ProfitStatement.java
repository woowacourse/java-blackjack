package blackjack.dto;

import blackjack.model.money.Profit;
import blackjack.model.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record ProfitStatement(String name, int profit) {

    public static List<ProfitStatement> createNameProfits(final Map<Player, Profit> playerProfit) {
        final List<ProfitStatement> profitStatements = new ArrayList<>();
        for (Player player : playerProfit.keySet()) {
            final int profit = playerProfit.get(player).getProfit();
            profitStatements.add(new ProfitStatement(player.getName(), profit));
        }
        return profitStatements;
    }
}
