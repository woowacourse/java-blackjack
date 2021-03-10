package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;

import java.util.List;
import java.util.Objects;

import static blackjack.domain.BlackjackConstant.BLACKJACK_SCORE;

public abstract class Participant {
    
    protected final String name;
    protected final CardHand cardHand;
    
    Participant(String name, CardHand cardHand) {
        this.name = name;
        this.cardHand = cardHand;
    }
    
    public List<Card> getCards() {
        return cardHand.getCards();
    }
    
    public int sumCardHand() {
        return cardHand.sum();
    }
    
    public String getName() {
        return name;
    }
    
    public void receive(Card card) {
        cardHand.add(card);
    }
    
    public boolean isBust() {
        return sumCardHand() > BLACKJACK_SCORE;
    }
    
    public abstract boolean canReceive();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(name, that.name) && Objects.equals(cardHand, that.cardHand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cardHand);
    }
}