package blackjack.domain.deck;

import blackjack.domain.card.Card;

import java.util.List;

@FunctionalInterface
public interface BlackjackCardHandInitializer {
    
    List<Card> handoutInitialCards();
}
