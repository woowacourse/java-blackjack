package BlackJack.domain.Card;

import java.util.*;

public class CardFactory {

    private final Queue<Card> deck;

    public CardFactory() {
        List<Card> cards = new ArrayList<>();
        Arrays.stream(Shape.values())
                .forEach(shape -> Arrays.stream(Number.values())
                        .map(number -> new Card(shape, number))
                        .forEach(cards::add));
        Collections.shuffle(cards);
        deck = new LinkedList<>(cards);
    }

    public Cards initCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(deck.poll());
        cards.add(deck.poll());

        return new Cards(cards);
    }

    public Card drawOneCard() {
        return deck.poll();
    }

    public int size() {
        return deck.size();
    }
}
