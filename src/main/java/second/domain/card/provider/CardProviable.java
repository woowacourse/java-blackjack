package second.domain.card.provider;

import second.domain.card.Card;

@FunctionalInterface
public interface CardProviable {
    Card pickCard();
}
