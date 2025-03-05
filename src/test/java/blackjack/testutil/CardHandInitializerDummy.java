package blackjack.testutil;

import java.util.List;

import blackjack.domain.Card;
import blackjack.domain.CardHandInitializer;

public class CardHandInitializerDummy implements CardHandInitializer {
    
    @Override
    public List<Card> init() {
        return List.of();
    }
}
