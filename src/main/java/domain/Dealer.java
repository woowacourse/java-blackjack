package domain;

import domain.constant.GameResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer implements Participant {
    private static final int MAX_DEALER_HAND_VALUE = 16;

    private final Hand hand;

    Dealer(final Hand hand) {
        this.hand = hand;
    }

    public static Dealer init() {
        return new Dealer(Hand.init());
    }

    @Override
    public boolean isDrawable() {
        return hand.getCardsNumberSum() <= MAX_DEALER_HAND_VALUE;
    }

    @Override
    public void draw(final Deck deck) {
        hand.addCard(deck.drawn());
    }

    public GameResults getGameResults(final List<Player> players) {
        List<GameResult> dealerGameResults = new ArrayList<>();
        Map<Player, GameResult> playerGameResults = new HashMap<>();

        players.forEach(player -> match(player, dealerGameResults, playerGameResults));

        return new GameResults(dealerGameResults, playerGameResults);
    }

    private void match(Player player, List<GameResult> dealerGameResults, Map<Player, GameResult> playerGameResults) {
        if (isDealerWin(player)) {
            dealerWin(player, dealerGameResults, playerGameResults);
            return;
        }
        dealerLose(player, dealerGameResults, playerGameResults);
    }

    private boolean isDealerWin(final Player player) {
        return hand.isBlackJack() || (hand.getCardsNumberSum() > player.getPlayerHandStatus().cardNumberSum());
    }

    private void dealerWin(Player player, List<GameResult> dealerGameResults, Map<Player, GameResult> playerGameResults) {
        dealerGameResults.add(GameResult.WIN);
        playerGameResults.put(player, GameResult.LOSE);
    }

    private void dealerLose(Player player, List<GameResult> dealerGameResults, Map<Player, GameResult> playerGameResults) {
        dealerGameResults.add(GameResult.LOSE);
        playerGameResults.put(player, GameResult.WIN);
    }
}
