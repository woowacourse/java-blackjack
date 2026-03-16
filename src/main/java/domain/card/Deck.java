package domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
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

    public List<Card> drawInitialCards() {
        List<Card> firstHandCards = new ArrayList<>();
        for (int index = 0; index < 2; index++) {
            firstHandCards.add(drawCard());
        }
        return firstHandCards;
    }

    public Card drawCard() {
        try {
            return cards.pop();
        } catch (NoSuchElementException exception) {
            throw new IllegalStateException("덱이 비었습니다");
        }
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
