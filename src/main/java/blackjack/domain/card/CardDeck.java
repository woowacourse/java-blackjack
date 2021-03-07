package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<Card> cards = new ArrayList<>();

    public CardDeck() {
        initialize();
    }

    private void initialize() {
        for (final CardSuit type : CardSuit.values()) {
            Arrays.stream(CardLetter.values())
                    .forEach(number -> cards.add(new Card(number, type)));
        }
        Collections.shuffle(cards);
    }

    public Card distribute() {
        return cards.remove(0);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
