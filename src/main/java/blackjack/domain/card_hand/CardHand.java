package blackjack.domain.card_hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.domain.Card;

public class CardHand {
    
    private final List<Card> cards = new ArrayList<>();
    
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
    
    public void addCard(Card card) {
        cards.add(card);
    }
    
    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }
}
