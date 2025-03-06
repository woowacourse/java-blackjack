package blackjack.domain.deck;

import blackjack.domain.Card;

@FunctionalInterface
public interface CardDrawer {
    
    Card draw();
}
