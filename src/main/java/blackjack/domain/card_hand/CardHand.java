package blackjack.domain.card_hand;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CardHand {
    
    private final List<Card> cards = new ArrayList<>();
    
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
    
    public void addCard(final Card card) {
        cards.add(card);
    }
    
    public void addCards(final List<Card> cards) {
        this.cards.addAll(cards);
    }
    
    public int getCardCount() {
        return cards.size();
    }
}
