package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResults {

    private final Map<Player, Integer> gameResults;

    public GameResults(final Dealer dealer, final List<Gambler> gamblers) {
        gameResults = new LinkedHashMap<>();

        for (Gambler gambler : gamblers) {
            int compared = gambler.compareWithOtherPlayer(dealer);

            if (compared == -1) {
                int multiplier = gambler.getCards().calculateBetAmountByMultiplier(1.0);
                gameResults.merge(dealer, multiplier, Integer::sum);
                gameResults.merge(gambler, -multiplier, Integer::sum);
            }

            if (compared == 1) {
                int multiplier = gambler.getCards().calculateBetAmountByMultiplier(1.0);
                if (gambler.isBlackJack()) {
                    multiplier = gambler.getCards().calculateBetAmountByMultiplier(1.5);
                }
                gameResults.merge(dealer, -multiplier, Integer::sum);
                gameResults.merge(gambler, multiplier, Integer::sum);
            }

            if (compared == 0) {
                int multiplier = gambler.getCards().calculateBetAmountByMultiplier(0);
                gameResults.merge(dealer, multiplier, Integer::sum);
                gameResults.merge(gambler, multiplier, Integer::sum);
            }
        }
    }

    public Map<Player, Integer> getGameResults() {
        return gameResults;
    }
}
