package blackjack.testutil;

import blackjack.domain.Card;
import blackjack.domain.CardHandInitializer;

import java.util.List;

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
