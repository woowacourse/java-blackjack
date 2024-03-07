package blackjack.domain;

import java.util.List;

public class Game {

    private final Dealer dealer;
    private final Players players;


    public Game(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void initializeHand() {
        dealer.shuffle();
        for (Player player : players.getPlayers()) {
            initHand(player);
        }
        initHand(dealer);
    }

    private void initHand(Player player) {
        List<Card> cards = this.dealer.doubleDraw();
        for (Card card : cards) {
            player.putCard(card);
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
