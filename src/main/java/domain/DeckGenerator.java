package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeckGenerator {
    public Deck generateDeck() {
        List<Card> cards = Arrays.stream(CardShape.values())
                .flatMap(cardShape ->
                        Arrays.stream(CardNumber.values())
                            .map(cardNumber -> new Card(cardShape, cardNumber)))
                .toList();
        return new Deck(new Cards(cards));
    }
}
