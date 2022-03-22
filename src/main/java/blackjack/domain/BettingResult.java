package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BettingResult {
    private static final int REVERSE_EARNING = -1;
    private final Map<Participant, Integer> bettingResult;

    private BettingResult(Map<Participant, Integer> bettingResult) {
        this.bettingResult = bettingResult;
    }

    public static BettingResult of(Dealer dealer, List<Player> players) {
        Map<Participant, Integer> playersResult = new LinkedHashMap<>();
        for (Player player : players) {
            GameResult gameResult = GameResult.compareScore(dealer, player);
            int earning = (int) gameResult.getEarnRate() * player.getBetMoney();
            playersResult.merge(dealer, getDealerEarning(earning), Integer::sum);
            playersResult.put(player, earning);
        }
        return new BettingResult(playersResult);
    }

    private static int getDealerEarning(int earning) {
        return earning * REVERSE_EARNING;
    }

    public Map<Participant, Integer> getBettingResult() {
        return bettingResult;
    }
}
