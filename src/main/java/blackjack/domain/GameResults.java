package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResults {

    public static final int MULTIPLE_DREW = 0;
    public static final double MULTIPLE_LOSE = -1.0;
    public static final double MULTIPLE_WIN = 1.0;
    public static final double MULTIPLE_BLACKJACK_WIN = 1.5;

    private final Map<Player, Integer> gameResults;

    public GameResults(final Dealer dealer, final List<Gambler> gamblers) {
        gameResults = new LinkedHashMap<>();
        for (Gambler gambler : gamblers) {
            int compared = compareGamblerWithDealer(gambler, dealer);
            double multiple = calculateMultiple(gambler, compared);
            int multiplier = gambler.calculateBetAmount(multiple);

            updateGameResults(dealer, gambler, multiplier);
        }
    }

    private int compareGamblerWithDealer(final Gambler gambler, final Dealer dealer) {
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

    private double calculateMultiple(final Gambler gambler, final int compared) {
        if (compared == 0) {
            return MULTIPLE_DREW;
        }
        if (compared == -1) {
            return MULTIPLE_LOSE;
        }
        if (gambler.isBlackJack()) {
            return MULTIPLE_BLACKJACK_WIN;
        }
        return MULTIPLE_WIN;
    }

    private void updateGameResults(final Dealer dealer, final Gambler gambler, final int multiplier) {
        gameResults.merge(dealer, -multiplier, Integer::sum);
        gameResults.merge(gambler, multiplier, Integer::sum);
    }

    public Map<Player, Integer> getGameResults() {
        return gameResults;
    }
}
