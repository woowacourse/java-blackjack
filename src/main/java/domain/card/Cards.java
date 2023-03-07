package domain.card;

import domain.card.shuffler.CardsShuffler;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public final class Cards {


    private final Deque<Card> cards;

    public Cards(CardsShuffler shuffler) {
        this.cards = shuffler.shuffleCards(initializeCards());
    }

    private static Deque<Card> initializeCards() {
        return Arrays.stream(Shape.values())
                .flatMap(shape -> Arrays.stream(Value.values())
                        .map(value -> new Card(value, shape)))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    public List<Card> giveInitialCards() {
        return List.of(getCard(), getCard());
    }

    public boolean contains(final Card card) {
        return cards.contains(card);
    }

    public Card getCard() {
        return cards.pop();
    }
}
