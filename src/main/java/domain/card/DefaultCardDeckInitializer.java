package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultCardDeckInitializer implements CardDeckInitializer {

    @Override
    public List<Card> initialize() {
        List<Card> blackjackGameCards = new ArrayList<>();
        for (CardDenomination emblem : CardDenomination.values()) {
            for (CardEmblem denomination : CardEmblem.values()) {
                Card card = Card.of(emblem, denomination);
                blackjackGameCards.add(card);
            }
        }

        shuffle(blackjackGameCards);
        return blackjackGameCards;
    }

    private void shuffle(List<Card> blackjackGameCards) {
        Collections.shuffle(blackjackGameCards);
    }

}
