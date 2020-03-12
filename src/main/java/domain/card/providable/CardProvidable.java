package domain.card.providable;

import domain.card.Card;

@FunctionalInterface
public interface CardProvidable {
    Card giveCard();
}
