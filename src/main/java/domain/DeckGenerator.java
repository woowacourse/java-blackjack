package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DeckGenerator {
    public Deck generateDeck() {
        List<Card> cards = Arrays.stream(CardShape.values())
                .flatMap(cardShape ->
                        Arrays.stream(CardNumber.values())
                                .map(cardNumber -> new Card(cardShape, cardNumber)))
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        return new Deck(cards);
    }
}
