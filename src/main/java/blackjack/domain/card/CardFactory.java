package blackjack.domain.card;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CardFactory {

    private static final List<Card> cachingCard = new ArrayList<>();

    static {
        for (CardPattern pattern : CardPattern.values()) {
            addAllNumberOf(pattern);
        }
        System.out.println(cachingCard.size());
    }

    private static void addAllNumberOf(CardPattern pattern) {
        for (CardNumber number : CardNumber.values()) {
            cachingCard.add(new Card(pattern, number));
        }
    }

    public static Card of(CardPattern pattern, CardNumber number) {
        return cachingCard.stream()
            .filter(card -> card.isContain(pattern, number))
            .findAny()
            .orElse(null);
    }

    public static List<Card> createDeck() {
        return new LinkedList<>(cachingCard);
    }
}
