package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardDeckCreator {

    public CardDeck create() {
        List<Card> cards = createCards(CardShape.values(), CardNumber.values());
        Collections.shuffle(cards);
        return new CardDeck(cards);
    }

    private List<Card> createCards(CardShape[] cardShapes, CardNumber[] cardNumbers) {
        return Arrays.stream(cardShapes)
                .flatMap(shape -> Arrays.stream(cardNumbers)
                        .map(number -> new Card(shape, number)))
                .collect(Collectors.toList());
    }
}
