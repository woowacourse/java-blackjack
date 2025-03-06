package blackjack.domain.deck;

import blackjack.domain.card.Card;

import java.util.List;

@FunctionalInterface
public interface CardHandInitializer {
    
    List<Card> init();
}
