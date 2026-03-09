package domain.card;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
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

    public List<Card> dealFirstHandCards() {
        List<Card> firstHandCards = new ArrayList<>();
        for (int index = 0; index < 2; index++) {
            firstHandCards.add(drawCard());
        }
        return firstHandCards;
    }

    public Card drawCard() {
        return cards.pop();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
