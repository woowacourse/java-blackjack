package blackjack.model.deck;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class CardDeck {
    private static final int INITIAL_CARD_SIZE = 2;
    private static final List<Card> CACHE = Collections.unmodifiableList(new ArrayList<>(generate()));

    private static List<Card> generate() {
        return Arrays.stream(CardNumber.values())
                .map(CardDeck::generateCardsByCardNumber)
                .flatMap(List::stream)
                .toList();
    }

    private static List<Card> generateCardsByCardNumber(CardNumber cardNumber) {
        return Arrays.stream(CardShape.values())
                .map(cardShape -> new Card(cardNumber, cardShape))
                .toList();
    }

    public static Card drawCard() {
        CardNumber cardNumber = CardNumber.generate();
        CardShape cardShape = CardShape.generate();
        return CACHE.stream()
                .filter(card -> card.getCardNumber() == cardNumber && card.getCardShape() == cardShape)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("해당되는 카드가 없습니다."));
    }

    public static List<Card> drawCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INITIAL_CARD_SIZE; i++) {
            cards.add(drawCard());
        }
        return cards;
    }
}
