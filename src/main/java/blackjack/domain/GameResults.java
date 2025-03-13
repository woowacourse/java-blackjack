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
            int compared = compareGamblerWithDealer(gambler, dealer);
            int multiplier = gambler.getHand().calculateBetAmountByMultiplier(1.0);
            if (compared == -1) {
                multiplier = gambler.getHand().calculateBetAmountByMultiplier(-1.0);
            }

            if (compared == 0) {
                multiplier = gambler.getHand().calculateBetAmountByMultiplier(0);
            }

            if (gambler.isBlackJack()) {
                multiplier = gambler.getHand().calculateBetAmountByMultiplier(1.5);
            }

            updateGameResults(dealer, gambler, multiplier);
        }
    }

    private void updateGameResults(final Dealer dealer, final Gambler gambler, final int multiplier) {
        gameResults.merge(dealer, -multiplier, Integer::sum);
        gameResults.merge(gambler, multiplier, Integer::sum);
    }

    public int compareGamblerWithDealer(final Gambler gambler, final Dealer dealer) {
        if (gambler.isNotBust() && dealer.isBust()) {
            return 1;
        }
        if (dealer.isBust() && gambler.isBust()) {
            return 0;
        }
        if (dealer.isNotBust() && gambler.isBust()) {
            return -1;
        }
        if (dealer.isBlackJack() || gambler.isBlackJack()) {
            if (dealer.isBlackJack() && gambler.isBlackJack()) {
                return 0;
            }
            if (gambler.isBlackJack()) {
                return 1;
            }
            return -1;
        }
        return Integer.compare(gambler.getCardScore(), dealer.getCardScore());
    }

    public Map<Player, Integer> getGameResults() {
        return gameResults;
    }
}
