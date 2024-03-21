package model.participant;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import model.card.Card;

public class Entrant {
    private final Dealer dealer;
    private final Queue<Player> players;

    public Entrant(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = new ArrayDeque<>(players);
    }

    public void hitDealer(Card card) {
        dealer.hitCard(card);
    }

    public boolean isDealerHitAllowed() {
        return dealer.canHit();
    }

    public void hitPlayer(Card card) {
        getCurrentPlayer().hitCard(card);
    }

    public void turnOverPlayer() {
        getCurrentPlayer().turnOver();
        moveToNextPlayer();
    }

    public Player getNextAvailablePlayer() {
        Player currentPlayer = getCurrentPlayer();
        if (currentPlayer.canHit()) {
            return currentPlayer;
        }
        moveToNextPlayer();
        return getNextAvailablePlayer();
    }

    private void moveToNextPlayer() {
        players.add(players.poll());
    }

    public boolean hasAvailablePlayer() {
        return players.stream()
                .anyMatch(Player::canHit);
    }

    public void aggregateBettingResult() {
        players.forEach(player -> player.updateMatchResult(dealer.matchState, dealer.getHand()));
    }

    private Player getCurrentPlayer() {
        return Objects.requireNonNull(players.peek());
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public Dealer getDealer() {
        return dealer;
    }
}
