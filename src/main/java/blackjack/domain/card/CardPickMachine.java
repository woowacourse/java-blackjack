package blackjack.domain.card;

import blackjack.domain.strategy.NumberGenerator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CardPickMachine {
    private final Set<Card> pickedCards = new HashSet<>();

    public Card pickCard(List<Card> cardDeck, NumberGenerator numberGenerator) {
        Card card = cardDeck.get(numberGenerator.generateNumber());

        while (pickedCards.contains(card)) {
            card = cardDeck.get(numberGenerator.generateNumber());
        }

        pickedCards.add(card);

        return card;
    }
}
