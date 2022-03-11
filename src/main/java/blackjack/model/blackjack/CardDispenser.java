package blackjack.model.blackjack;

import blackjack.model.card.Card;

public interface CardDispenser {

    Card issue();

    static CardDispenser shuffledCardDispenser() {
        return new ShuffledCardDispenser();
    }

}
