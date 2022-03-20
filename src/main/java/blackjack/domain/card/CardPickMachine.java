package blackjack.domain.card;

import java.util.HashSet;
import java.util.Set;

public class CardPickMachine {
    private final Set<Card> pickedCards = new HashSet<>();

    public Card pickCard(int index) {
        Card card = Card.of(index);

        while (pickedCards.contains(card)) {
            card = Card.of(index);
        }

        pickedCards.add(card);

        return card;
    }
}
