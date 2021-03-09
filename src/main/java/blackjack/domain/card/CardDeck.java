package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDeck {

    private static final List<Card> cards = new ArrayList<>();

    static {
        for (final CardType type : CardType.values()) {
            Arrays.stream(CardNumber.values())
                .forEach(number -> cards.add(new Card(number, type)));
        }
        Collections.shuffle(cards);
    }

    private CardDeck() {
    }

    public static List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public static Card distribute() {
        return cards.remove(0);
    }
}
