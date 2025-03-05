package model;

import java.util.List;

public class GameManager {

    private final Dealer dealer;
    private final Players players;
    private final CardDeck deck = new CardDeck();

    public GameManager(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void divideCardByPlayer(Player player, int amount) {
        List<Card> pickCards = deck.pickCard(amount);
        player.addCards(pickCards);
    }

/*    public void divide() {
        return
    }*/
}
