package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    public static final int MULTIPLE_DREW = 0;
    public static final double MULTIPLE_LOSE = -1.0;
    public static final double MULTIPLE_WIN = 1.0;
    public static final double MULTIPLE_BLACKJACK_WIN = 1.5;

    private final Map<Player, Integer> gameResults = new LinkedHashMap<>();

    public void processResult(final Dealer dealer, final Gambler gambler) {
        int compared = compareGamblerWithDealer(dealer, gambler);
        double multiple = calculateMultiple(gambler, compared);
        int multiplier = gambler.calculateBetAmount(multiple);

        updateGameResults(dealer, gambler, multiplier);
    }

    private int compareGamblerWithDealer(final Dealer dealer, final Gambler gambler) {
        if (isTieOutcome(dealer, gambler)) {
            return 0;
        }
        if (isGamblerWinCondition(dealer, gambler)) {
            return 1;
        }
        if (isDealerWinCondition(dealer, gambler)) {
            return -1;
        }
        return Integer.compare(gambler.getCardScore(), dealer.getCardScore());
    }

    private boolean isTieOutcome(final Dealer dealer, final Gambler gambler) {
        return (dealer.isBust() && gambler.isBust()) ||
                (dealer.isBlackJack() && gambler.isBlackJack());
    }

    private boolean isGamblerWinCondition(final Dealer dealer, final Gambler gambler) {
        return (gambler.isNotBust() && dealer.isBust()) ||
                gambler.isBlackJack();
    }

    private boolean isDealerWinCondition(final Dealer dealer, final Gambler gambler) {
        return (dealer.isNotBust() && gambler.isBust()) ||
                dealer.isBlackJack();
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
        return Collections.unmodifiableMap(gameResults);
    }
}
