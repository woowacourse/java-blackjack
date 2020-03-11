package domain;

import domain.card.Card;

@FunctionalInterface
public interface CardProvider {
    public Card giveCard();
}
