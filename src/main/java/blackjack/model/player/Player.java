package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.game.ReceivedCards;

public abstract class Player {
    private final ReceivedCards receivedCards = new ReceivedCards();

    public void putCard(Card card) {
        receivedCards.receive(card);
    }

    public ReceivedCards getReceivedCards() {
        return receivedCards;
    }

    public boolean isBust() {
        return receivedCards.isBust();
    }

    public int calculatePoint() {
        return receivedCards.calculateTotalPoint();
    }

    public boolean isBlackJack() {
        long specialCardCount = receivedCards.getSpecialCardCount();
        long aceCardCount = receivedCards.getAceCardCount();
        return specialCardCount == 1 && aceCardCount == 1;
    }
}
