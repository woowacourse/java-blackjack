package blackjack;

import java.util.List;
import java.util.function.Predicate;

public class Game {

    public static final int DEALER_HIT_THRESHOLD = 16;

    private final Dealer dealer;
    private final List<Player> players;

    public Game(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = List.copyOf(players);
    }

    public void dealInitialCards() {
        for (Player player : players) {
            dealCard(player);
            dealCard(player);
        }
        dealCard(dealer);
        dealCard(dealer);
    }

    private void dealCard(Participant participant) {
        Card card = dealer.drawCard();
        participant.receiveHand(card);
    }

    public void askHitForAllPlayer(Predicate<String> playerHitDecision, PlayerAction playerAction) {
        for (Player player : players) {
            askHit(player, playerHitDecision, playerAction);
        }
    }

    private void askHit(Player player, Predicate<String> playerHitDecision, PlayerAction playerAction) {
        if (player.isBlackjack()) {
            return;
        }
        playTurn(player, playerHitDecision, playerAction);
    }

    private void playTurn(Player player, Predicate<String> playerHitDecision, PlayerAction playerAction) {
        boolean isFirstTurn = true;
        while (!player.isBust() && shouldPlayerHit(player, playerHitDecision)) {
            dealCard(player);
            playerAction.accept(player);
            isFirstTurn = false;
        }
        if (isFirstTurn) {
            playerAction.accept(player);
        }
    }

    private boolean shouldPlayerHit(Player player, Predicate<String> playerHitDecision) {
        return playerHitDecision.test(player.getName());
    }

    public boolean dealerHit() {
        if (dealer.getTotal() <= DEALER_HIT_THRESHOLD) {
            dealCard(dealer);
            return true;
        }
        return false;
    }

    public Card getDealerVisibleCard() {
        return dealer.getVisibleCard();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
