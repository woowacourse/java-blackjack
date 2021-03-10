package blackjack.domain;

import blackjack.domain.user.Users;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Result {
    private final List<Outcome> dealerOutcomes;
    private final Map<String, Outcome> playerOutcomes = new LinkedHashMap<>();

    public Result(Users users) {
        dealerOutcomes = users.getPlayers().stream()
                .map(player ->
                        Outcome.findOutcome(users.getDealer().score(), player.score()))
                .collect(Collectors.toList());

        for (int i = 0; i < dealerOutcomes.size(); i++) {
            playerOutcomes.put(users.getPlayers().get(i).getName(), dealerOutcomes.get(i).reverse());
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
