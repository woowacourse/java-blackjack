package blackjack;

import java.util.List;
import java.util.function.Predicate;

public class Game {

    private static final int DEALER_HIT_THRESHOLD = 16;

    private final Dealer dealer;
    private final List<Player> players;

    public Game(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
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

    public void askHitForAllPlayer(Predicate<String> playerHitDecision) {
        for (Player player : players) {
            askHit(playerHitDecision, player);
        }
    }

    private void askHit(Predicate<String> playerHitDecision, Player player) {
        if (player.isBlackjack()) {
            return;
        }
        playTurn(player, playerHitDecision);
    }

    private void playTurn(Player player, Predicate<String> playerHitDecision) {
        while (!player.isBust() && shouldPlayerHit(player, playerHitDecision)) {
            dealCard(player);
        }
    }

    private boolean shouldPlayerHit(Player player, Predicate<String> playerHitDecision) {
        return playerHitDecision.test(player.getName());
    }

    public void dealerHit() {
        if (dealer.getTotal() <= DEALER_HIT_THRESHOLD) {
            dealCard(dealer);
        }
    }
}
