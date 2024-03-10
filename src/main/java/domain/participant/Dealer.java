package domain.participant;

import domain.*;
import domain.constant.GameResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {
    private static final int MAX_DEALER_HAND_VALUE = 16;

    Dealer(final Hand hand) {
        super(hand);
    }

    public static Dealer init() {
        return new Dealer(Hand.init());
    }

    @Override
    public boolean isDrawable() {
        return hand.getCardsNumberSum() <= MAX_DEALER_HAND_VALUE;
    }

    public GameResults getGameResults(final List<Player> players) {
        List<GameResult> dealerGameResults = new ArrayList<>();
        Map<Player, GameResult> playerGameResults = new HashMap<>();

        players.forEach(player -> match(player, dealerGameResults, playerGameResults));

        return new GameResults(dealerGameResults, playerGameResults);
    }

    private void match(final Player player, final List<GameResult> dealerGameResults, final Map<Player, GameResult> playerGameResults) {
        if (isDealerWin(player)) {
            dealerWin(player, dealerGameResults, playerGameResults);
            return;
        }
        dealerLose(player, dealerGameResults, playerGameResults);
    }

    private boolean isDealerWin(final Player player) {
        if (player.isNotBurst()) {
            return hand.isNotBurst() && (hand.getCardsNumberSum() >= player.getHandSum());
        }
        return true;
    }

    private void dealerWin(final Player player, final List<GameResult> dealerGameResults, final Map<Player, GameResult> playerGameResults) {
        dealerGameResults.add(GameResult.WIN);
        playerGameResults.put(player, GameResult.LOSE);
    }

    private void dealerLose(final Player player, final List<GameResult> dealerGameResults, final Map<Player, GameResult> playerGameResults) {
        dealerGameResults.add(GameResult.LOSE);
        playerGameResults.put(player, GameResult.WIN);
    }
}
