package blackjack.domain.participant;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import java.util.List;

public abstract class Participant {

    protected final CardHand cardHand;

    public Participant(CardHand cardHand) {
        this.cardHand = cardHand;
    }

    abstract boolean canHit();
    public abstract List<Card> showStartCards();

    public abstract String getName();

    public boolean isBust() {
        return cardHand.isBust();
    }

    public boolean isBlackjack() {
        return cardHand.isBlackjack();
    }

    public void receiveCard(Card card) {
        cardHand.add(card);
    }

    public Score getScore() {
        return cardHand.getScore();
    }

    public List<Card> getCardDeck() {
        return cardHand.getCards();
    }

    public int getCardSize() {
        return cardHand.deckSize();
    }
}
