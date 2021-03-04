package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private static final int FIRST_INDEX = 0;

    private static final List<Card> DECK;

    static {
        DECK = Arrays.stream(CardPattern.values())
                .flatMap(cardPattern -> Arrays.stream(CardNumber.values())
                        .map(cardNumber -> new Card(cardPattern, cardNumber)))
                .collect(Collectors.toList());
    }

    public static void shuffleCards() {
        Collections.shuffle(DECK);
    }

    public static Card dealCard() {
        return DECK.remove(FIRST_INDEX);
    }
}
