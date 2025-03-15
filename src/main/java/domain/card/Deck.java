package domain.card;

import static util.ExceptionConstants.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private static final String DECK_IS_EMPTY = "더이상 남아있는 카드가 없습니다.";
    private final List<Card> deck;

    public Deck() {
        deck = initializeDeck();
        Collections.shuffle(deck);
    }

    public Deck(List<Card> deck) {
        this.deck = deck;
    }

    public Card draw() {
        checkRemainingCard();
        return deck.removeFirst();
    }

    private List<Card> initializeDeck() {
        return Arrays.stream(CardType.values())
                .flatMap(cardType -> Arrays.stream(CardNumberType.values())
                        .map(cardNumberType -> new Card(cardNumberType, cardType)))
                .collect(Collectors.toList());
    }

    private void checkRemainingCard() {
        if (deck.isEmpty()) {
            throw new IllegalStateException(ERROR_HEADER + DECK_IS_EMPTY);
        }
    }
}
