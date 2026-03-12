package blackjack.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class FinalIncome {

    private final int dealerIncome;
    private final Map<Player, Integer> playerIncomeResults;

    public FinalIncome(int dealerIncome, Map<Player, Integer> playerIncomeResults) {
        this.dealerIncome = dealerIncome;
        this.playerIncomeResults = new LinkedHashMap<>(playerIncomeResults);
    }

    public int getDealerIncome() {
        return dealerIncome;
    }

    public Map<Player, Integer> getPlayerIncomeResults() {
        return Collections.unmodifiableMap(playerIncomeResults);
    }

    public int getIncomeOf(Player player) {
        return playerIncomeResults.get(player);
    }

}
