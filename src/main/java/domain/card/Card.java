package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private CardNumber cardNumber;
    private CardSuitSymbol cardSuitSymbol;
    private static final List<Card> CARDS;

    private Card(CardNumber cardNumber, CardSuitSymbol cardSuitSymbol) {
        this.cardNumber = cardNumber;
        this.cardSuitSymbol = cardSuitSymbol;
    }

    static {
        CARDS = new ArrayList<>();
        for (CardNumber cardNumber : CardNumber.values()) {
            addCard(cardNumber);
        }
    }

    private static void addCard(CardNumber cardNumber) {
        for (CardSuitSymbol cardSuitSymbol : CardSuitSymbol.values()) {
            CARDS.add(new Card(cardNumber, cardSuitSymbol));
        }
    }

    public static Card of(CardNumber cardNumber, CardSuitSymbol cardSuitSymbol) {
        return CARDS.stream()
                .filter(card -> card.isSameCard(cardNumber, cardSuitSymbol))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private boolean isSameCard(CardNumber cardNumber, CardSuitSymbol cardSuitSymbol) {
        return this.cardNumber.equals(cardNumber) && this.cardSuitSymbol.equals(cardSuitSymbol);
    }

    public static List<Card> getCards() {
        return new ArrayList<>(CARDS);
    }

    public boolean isAce() {
        return this.cardNumber.equals(CardNumber.ACE);
    }

    public int getCardNumber() {
        return this.cardNumber.getCardNumber();
    }

    @Override
    public String toString() {
        return getCardNumber() + this.cardSuitSymbol.getSuitSymbol();
    }
}
