package domain.card;

import static util.ExceptionConstants.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final String DECK_IS_EMPTY = "더이상 남아있는 카드가 없습니다.";
    private final List<Card> deck;

    public Deck() {
        deck = initializeDeck();
        Collections.shuffle(deck);
    }

    public Card draw() {
        checkRemainingCard();
        return deck.removeFirst();
    }

    private List<Card> initializeDeck() {
        List<Card> result = new ArrayList<>();

        for (CardType cardType : CardType.values()) {
            for (CardNumberType cardNumberType : CardNumberType.values()) {
                result.add(new Card(cardNumberType, cardType));
            }
        }
        return result;
    }

    private void checkRemainingCard() {
        if(deck.isEmpty()) {
            throw new IllegalStateException(ERROR_HEADER + DECK_IS_EMPTY);
        }
    }
}
