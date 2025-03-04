package blackjack.testutil;

import blackjack.domain.Card;
import blackjack.domain.CardHandInitializer;

import java.util.List;

public class CardHandInitializerDummy implements CardHandInitializer {
    
    @Override
    public List<Card> init() {
        return List.of();
    }
}
