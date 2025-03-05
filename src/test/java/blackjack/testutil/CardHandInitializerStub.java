package blackjack.testutil;

import java.util.List;

import blackjack.domain.Card;
import blackjack.domain.CardHandInitializer;

public class CardHandInitializerStub implements CardHandInitializer {
    
    private final List<Card> cards;
    
    public CardHandInitializerStub(List<Card> cards) {
        this.cards = cards;
    }
    
    @Override
    public List<Card> init() {
        return this.cards;
    }
}
