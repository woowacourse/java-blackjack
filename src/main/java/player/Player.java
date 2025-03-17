package player;

import card.Card;
import card.HandCards;
import card.Deck;
import java.util.List;

public class Player {
    private final HandCards handCards;

    public Player() {
        this.handCards = new HandCards();
    }

    public void receiveInitialCards(Deck deck) {
        handCards.addAll(deck.drawCards(2));
    }

    public void drawOneCard(Deck deck) {
        handCards.addAll(deck.drawCards(1));
    }

    public int computeOptimalSum() {
        return handCards.computeOptimalSum();
    }

    public boolean isBust() {
        return handCards.isBust();
    }

    public boolean isBlackjack() {
        return handCards.isBlackjack();
    }

    public HandCards getHandCards() {
        return handCards;
    }

    public List<Card> getCards() {
        return handCards.getCards();
    }

    public List<Card> getCards(int count) {
        return handCards.getCards(count);
    }
}
