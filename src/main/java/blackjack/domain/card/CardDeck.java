package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<Card> cards;

    public CardDeck() {
        cards = initialize();
    }

    public List<Card> initialize() {
        List<Card> cards = new ArrayList<>();
        for (final CardType type : CardType.values()) {
            Arrays.stream(CardNumber.values())
                    .forEach(number -> cards.add(new Card(number, type)));
        }
        Collections.shuffle(cards);
        return cards;
    }

    public Card distribute() {
        return cards.remove(0);
    }
}
