package first.domain.card.providable;

import first.domain.card.Card;

@FunctionalInterface
public interface CardProvidable {
    Card giveCard();
}
