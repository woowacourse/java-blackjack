package player;

import card.Card;
import card.Deck;
import card.HandCards;
import java.util.List;

public class Dealer {
    private static final int ADD_CARD_THRESHOLD = 16;

    private final Player player;
    private final Deck deck;

    public Dealer(Deck deck) {
        this.player = new Player();
        this.deck = deck;
    }

    public List<Card> openInitialCards() {
        return player.getCards(1);
    }

    public void receiveInitialCards() {
        player.receiveInitialCards(deck);
    }

    private void drawOneCard() {
        player.drawOneCard(deck);
    }

    public boolean drawOneCardIfLowScore() {
        if (computeOptimalSum() <= ADD_CARD_THRESHOLD) {
            drawOneCard();
            return true;
        }
        return false;
    }

    public int computeOptimalSum() {
        return player.computeOptimalSum();
    }

    public boolean isBust() {
        return player.isBust();
    }

    public boolean isBlackjack() {
        return player.isBlackjack();
    }

    public HandCards getHandCards() {
        return player.getHandCards();
    }

    public List<Card> getCards() {
        return player.getCards();
    }

    public Deck getDeck() {
        return deck;
    }
}
