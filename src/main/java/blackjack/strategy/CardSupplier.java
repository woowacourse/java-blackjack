package blackjack.strategy;

import blackjack.domain.card.Card;

@FunctionalInterface
public interface CardSupplier {

    Card getCard();
}
