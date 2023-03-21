package domain;

import domain.card.Card;

@FunctionalInterface
public interface CardGenerator {

	Card generate();
}
