package blackjack.domain.card_hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.domain.Card;
import blackjack.domain.CardHandInitializer;

public class CardHand {
    
    private final List<Card> cards = new ArrayList<>();
    
    // TODO : CardHand가 처음에 카드를 init하는게 맞는 역할인가?
    public CardHand(final CardHandInitializer initializer) {
        cards.addAll(initializer.init());
    }
    
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
    
    public void addCard(Card card) {
        cards.add(card);
    }
}
