package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class CardDeck {

    private static final Deque<Card> cards = new ArrayDeque<>();

    static {
        final List<Card> cardGroup = new ArrayList<>();
        for (final CardType type : CardType.values()) {
            Arrays.stream(CardNumber.values())
                .forEach(number -> cardGroup.add(new Card(number, type)));
        }
        Collections.shuffle(cardGroup);
        cards.addAll(cardGroup);
    }

    private CardDeck() {
    }

    public static Card distribute() {
        return cards.pop();
    }

    public static int size() {
        return cards.size();
    }
}
