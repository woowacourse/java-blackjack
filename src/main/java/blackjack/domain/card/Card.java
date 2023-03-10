package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Card {

    protected static final List<Card> cache = new ArrayList<>();

    private final CardNumber cardNumber;
    private final CardSuit cardSuit;

    public Card(CardNumber cardNumber, CardSuit cardSuit) {
        this.cardNumber = cardNumber;
        this.cardSuit = cardSuit;
    }

    static {
        for (CardNumber cardNumber : CardNumber.values()) {
            for (CardSuit cardSuit : CardSuit.values()) {
                cache.add(new Card(cardNumber, cardSuit));
            }
        }
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        Card other = (Card) obj;
        if ((this.cardSuit == other.cardSuit) && (this.cardNumber == other.cardNumber)) {
            return true;
        }
        return false;
    }
}
