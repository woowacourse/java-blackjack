package blackJack.domain.Card;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CardFactory {

    private static final int INIT_CARD_SIZE = 2;

    private final Queue<Card> deck;

    public CardFactory() {
        List<Card> cards = createCards();
        Collections.shuffle(cards);
        deck = new LinkedList<>(cards);
    }

    private List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        Arrays.stream(Shape.values())
                .forEach(shape -> Arrays.stream(Number.values())
                        .map(number -> Card.valueOf(shape, number))
                        .forEach(cards::add));
        return cards;
    }

    public Cards initCards() {
        List<Card> cards = IntStream.range(0, INIT_CARD_SIZE)
                .mapToObj(i -> drawOneCard())
                .collect(Collectors.toList());

        return new Cards(cards);
    }

    public Card drawOneCard() {
        return deck.poll();
    }

    public int size() {
        return deck.size();
    }
}
