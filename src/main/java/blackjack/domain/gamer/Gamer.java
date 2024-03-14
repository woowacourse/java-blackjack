package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;

import java.util.List;

public class Gamer {
    protected final CardHand cardHand;

    protected Gamer(CardHand cardHand) {
        this.cardHand = cardHand;
    }

    public void receiveInitCards(List<Card> cards) {
        cardHand.add(cards);
    }

    public void receiveCard(Card card) {
        cardHand.add(card);
    }

    public int getScore() {
        return cardHand.calculateScore();
    }

    public boolean isBust() {
        return cardHand.isBurst();
    }

    public boolean isNotBust() {
        return !isBust();
    }

    @Override
    public String toString() {
        return "Gamer{" +
                "cardHand=" + cardHand +
                '}';
    }

    public List<Card> getCardHand() {
        return cardHand.getCards();
    }
}
