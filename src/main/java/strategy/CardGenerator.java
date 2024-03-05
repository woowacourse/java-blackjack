package strategy;

import domain.card.Card;

public interface CardGenerator {

    boolean hasNext();
    Card nextCard();
}
