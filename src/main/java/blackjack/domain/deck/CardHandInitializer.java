package blackjack.domain.deck;

import blackjack.domain.Card;

import java.util.List;

@FunctionalInterface
public interface CardHandInitializer {
    
    List<Card> init();
}
