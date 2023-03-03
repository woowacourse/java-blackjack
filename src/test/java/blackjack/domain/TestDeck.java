package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Shape;

import java.util.List;
import java.util.stream.Collectors;

public class TestDeck implements Deck {
    private final List<Card> cards;
    int count = 0;

    public TestDeck(List<Integer> cardNumbers) {
        this.cards = cardNumbers.stream()
                .map(num -> new Card(Shape.HEART, CardNumber.of(num)))
                .collect(Collectors.toList());
    }

    @Override
    public Card drawCard() {
        if (count < cards.size()) {
            final Card card = cards.get(count);
            count++;
            return card;
        }
        return new Card(Shape.HEART, CardNumber.of(1));
    }
}
