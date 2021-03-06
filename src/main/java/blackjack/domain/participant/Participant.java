package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;

import java.util.List;

public abstract class Participant {
    
    protected final String name;
    protected final CardHand cardHand;
    
    public Participant(String name, CardHand cardHand) {
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
    
    public abstract boolean canReceive();
}