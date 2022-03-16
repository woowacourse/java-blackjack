package blackJack.domain.result;

import blackJack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackGameResult {

    private final Map<Player, OutCome> outComes = new LinkedHashMap<>();

    public void add(Player player, OutCome outCome) {
        outComes.put(player, outCome);
    }

    public Map<OutCome, Integer> calculateDealerResult() {
        final Map<OutCome, Integer> dealerGameScore = getWinOrLose();

        for (OutCome value : outComes.values()) {
            dealerGameScore.computeIfPresent(value, (k, v) -> v + 1);
        }
        swapResult(dealerGameScore);

        return dealerGameScore;
    }

    private Map<OutCome, Integer> getWinOrLose() {
        final Map<OutCome, Integer> dealerGameScore = new LinkedHashMap<>();

        for (OutCome value : OutCome.values()) {
            dealerGameScore.put(value, 0);
        }

        return dealerGameScore;
    }

    private void swapResult(Map<OutCome, Integer> dealerGameScore) {
        final int loseCounts = dealerGameScore.get(OutCome.WIN);
        dealerGameScore.put(OutCome.WIN, dealerGameScore.get(OutCome.LOSE));
        dealerGameScore.put(OutCome.LOSE, loseCounts);
    }

    public Map<Player, OutCome> getGameResult() {
        return outComes;
    }
}
