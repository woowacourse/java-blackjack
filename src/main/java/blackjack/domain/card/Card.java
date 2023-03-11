package blackjack.domain.card;

import blackjack.domain.vo.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Card {

    private static final List<Card> CACHE = new ArrayList<>();

    static {
        for (CardSuit suit : CardSuit.values()) {
            generateCard(suit);
        }
    }

    private final CardSuit cardSuit;
    private final CardNumber cardNumber;

    public Card(CardSuit cardSuit, CardNumber cardNumber) {
        this.cardSuit = cardSuit;
        this.cardNumber = cardNumber;
    }

    private static void generateCard(CardSuit suit) {
        for (CardNumber number : CardNumber.values()) {
            CACHE.add(new Card(suit, number));
        }
    }

    public static List<Card> values() {
        return List.copyOf(CACHE);
    }

    public boolean isAce() {
        return this.cardNumber.isAce();
    }

    public Score getScore() {
        return Score.of(cardNumber.getScore());
    }

    public CardSuit getSuit() {
        return cardSuit;
    }

    public CardNumber getNumber() {
        return cardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return cardSuit == card.cardSuit && cardNumber == card.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardSuit, cardNumber);
    }
}
