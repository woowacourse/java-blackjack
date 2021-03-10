package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Result {
    private final List<Outcome> dealerOutcomes;
    private final Map<String, Outcome> playerOutcomes = new LinkedHashMap<>();

    public Result(Dealer dealer, List<Player> players) {
        dealerOutcomes = players.stream()
                .map(player -> Outcome.findOutcome(dealer.score(), player.score()))
                .collect(Collectors.toList());
        for (int i = 0; i < dealerOutcomes.size(); i++) {
            playerOutcomes.put(players.get(i).getName(), dealerOutcomes.get(i).reverse());
        }
    }

    public Map<String, Outcome> getPlayerOutcomes() {
        return playerOutcomes;
    }

    public int findDealerWinCount() {
        return (int) dealerOutcomes.stream()
                .filter(Outcome::isWin)
                .count();
    }

    public int findDealerLoseCount() {
        return (int) dealerOutcomes.stream()
                .filter(Outcome::isLose)
                .count();
    }

    public int findDealerDrawCount() {
        return (int) dealerOutcomes.stream()
                .filter(Outcome::isDraw)
                .count();
    }
}
