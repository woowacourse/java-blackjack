package blackjack.domain.machine;

import java.util.HashSet;
import java.util.Set;

import blackjack.domain.strategy.NumberGenerator;

public class Cards {
    private final Set<Card> pickedCards = new HashSet<>();

    public Card pickCard(NumberGenerator numberGenerator) {
        int index = numberGenerator.generateNumber();
        Card card = Card.of(index);

        while (pickedCards.contains(card)) {
            index = numberGenerator.generateNumber();
            card = Card.of(index);
        }

        pickedCards.add(card);

        return card;
    }
}
