package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DeckGenerator {
    public static Deck generateDeck() {
        List<Card> cards = Arrays.stream(CardSuit.values())
                .flatMap(cardShape ->
                        Arrays.stream(CardRank.values())
                                .map(cardNumber -> new Card(cardShape, cardNumber)))
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        return new Deck(cards);
    }
}
