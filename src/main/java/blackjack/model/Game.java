package blackjack.model;

import blackjack.model.card.Card;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Game {

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

    public void askHitForAllPlayer(PlayerHandVisualizer playerHandVisualizer) {
        for (Player player : players) {
            askHit(player, playerHandVisualizer);
        }
    }

    private void askHit(Player player, PlayerHandVisualizer playerHandVisualizer) {
        if (player.isBlackjack()) {
            return;
        }
        playTurn(player, playerHandVisualizer);
    }

    private void playTurn(Player player, PlayerHandVisualizer playerHandVisualizer) {
        boolean isFirstTurn = true;
        while (player.shouldHit()) {
            dealCard(player);
            playerHandVisualizer.accept(player);
            isFirstTurn = false;
        }
        if (isFirstTurn) {
            playerHandVisualizer.accept(player);
        }
    }

    public boolean dealerHit() {
        if (dealer.shouldHit()) {
            dealCard(dealer);
            return true;
        }
        return false;
    }

    public Map<Player, MatchResult> judgeMatchResults() {
        Map<Player, MatchResult> results = new LinkedHashMap<>();
        for (Player player : players) {
            results.put(player, MatchResult.judge(dealer, player));
        }
        return results;
    }

    public Card getDealerVisibleCard() {
        return dealer.getVisibleCard();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Card> getDealerHand() {
        return List.copyOf(dealer.getHand());
    }

    public int getDealerTotal() {
        return dealer.getTotal();
    }
}
