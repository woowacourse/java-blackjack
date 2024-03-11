package blackjack.model.deck;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import java.util.Arrays;
import java.util.List;

public class DeckGenerator {
    public List<Card> generate() {
        return Arrays.stream(CardNumber.values())
                .map(this::generateCardsByCardNumber)
                .flatMap(List::stream)
                .toList();
    }

    private List<Card> generateCardsByCardNumber(CardNumber cardNumber) {
        return Arrays.stream(CardShape.values())
                .map(cardShape -> new Card(cardNumber, cardShape))
                .toList();
    }
}
