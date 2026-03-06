package domain;

import java.util.*;
import java.util.stream.Collectors;

public class Deck {
    private final Deque<Card> cards;

    public Deck() {
        cards = initialize();
    }

    private Deque<Card> initialize() {
        List<Card> cards = Arrays.stream(CardShape.values())
                .flatMap(shape -> Arrays.stream(CardNumber.values())
                        .map(number -> new Card(number, shape)))
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(cards);
        return new ArrayDeque<>(cards);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public List<Card> firstHandCards() {
        List<Card> handOutCards = new ArrayList<>();
        for (int index = 0; index < 2; index++) {
            handOutCards.add(drawCard());
        }
        return handOutCards;
    }

    public Card drawCard() {
        return cards.pop();
    }
}
