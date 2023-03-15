package domain.card;

import domain.card.shuffler.CardsShuffler;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public final class CardDeck {

    private final Deque<Card> cards;

    public CardDeck(List<Card> cards, CardsShuffler shuffler) {
        this.cards = shuffler.shuffleCards(new ArrayDeque<>(cards));
    }

    public CardDeck(CardsShuffler shuffler) {
        this(new ArrayList<>(initializeCards()), shuffler);
    }

    private static Deque<Card> initializeCards() {
        return Arrays.stream(Value.values())
                .flatMap(value -> Arrays.stream(Shape.values())
                        .map(shape -> new Card(value, shape)))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    public Cards giveInitialCards() {
        return new Cards(List.of(getCard(), getCard()));
    }

    public Card getCard() {
        return cards.removeLast();
    }
}
