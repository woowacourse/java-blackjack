package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardFactory {
    private static List<Card> cards;

    static {
        cards = setUp();
    }

    private static List<Card> setUp() {
        return Arrays.stream(Symbol.values())
                .flatMap(symbol -> Arrays.stream(Type.values())
                        .map(type -> new Card(symbol, type)))
                .collect(Collectors.toList());
    }

    public static List<Card> createCardDeck() {
        List<Card> cardDeck = new ArrayList<>(cards);
        Collections.shuffle(cardDeck);
        return cardDeck;
    }
}
