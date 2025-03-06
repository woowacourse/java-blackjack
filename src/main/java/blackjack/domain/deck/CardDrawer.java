package blackjack.domain.deck;

import blackjack.domain.card.Card;

@FunctionalInterface
public interface CardDrawer {
    
    Card draw();
}
