package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {
    private static final List<Card> cards;

    static {
        cards = new ArrayList<>();
        for (Type type : Type.values()) {
            for (Figure figure : Figure.values()) {
                cards.add(Card.of(type, figure));
            }
        }
    }

    public static List<Card> create() {
        Collections.shuffle(cards);
        return Collections.unmodifiableList(cards);
    }
}
