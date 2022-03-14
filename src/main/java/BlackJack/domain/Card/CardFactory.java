package BlackJack.domain.Card;

import java.util.*;

public class CardFactory {

    static Queue<Card> CARD_CACHE;

    static {
        List<Card> cards = new ArrayList<>();
        Arrays.stream(Shape.values())
                .forEach(shape -> Arrays.stream(Number.values())
                .map(number -> new Card(shape, number))
                .forEach(cards::add));
        Collections.shuffle(cards);
        CARD_CACHE = new LinkedList<>(cards);
    }

    public static Cards initCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(CARD_CACHE.poll());
        cards.add(CARD_CACHE.poll());

        return new Cards(cards);
    }

    public static Card drawOneCard() {
        return CARD_CACHE.poll();
    }

}
