package card;

import static util.ExceptionConstants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private static final String DECK_IS_EMPTY = "더이상 남아있는 카드가 없습니다.";
    private static final int INITIAL_HAND_COUNT = 2;
    private final List<Card> deck;

    public Deck() {
        this.deck = initializeDeck();
        Collections.shuffle(deck);
    }

    public Deck(List<Card> cards) {
        this.deck = cards;
    }

    public Hand drawDefaultHand() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INITIAL_HAND_COUNT; i++) {
            cards.add(drawCard());
        }
        return new Hand(cards);
    }

    public Card drawCard() {
        checkRemainingCard();
        return deck.removeLast();
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
