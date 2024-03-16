package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Card {
    private static final List<Card> CARDS;
    static {
        CARDS = Arrays.stream(CardType.values())
                .flatMap(suit -> Arrays.stream(CardNumber.values())
                        .map(denomination -> new Card(suit, denomination)))
                .collect(Collectors.toUnmodifiableList());
    }
    private final CardType cardType;
    private final CardNumber cardNumber;

    private Card(CardType cardType, CardNumber cardNumber) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
    }

    public static Card from(CardType cardType, CardNumber cardNumber) {
        return CARDS.stream()
                .filter(card -> card.cardType == cardType && card.cardNumber == cardNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 카드입니다."));
    }

    public static List<Card> initializeDeck() {
        return new ArrayList<>(CARDS);
    }

    public boolean isAce() {
        return cardNumber.equals(CardNumber.ACE);
    }

    public CardType getCardType() {
        return cardType;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }
}
