package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final Denomination denomination;
    private final Suit suit;

    public Card(Denomination denomination, Suit suit) {
        validateNull(denomination, suit);
        this.denomination = denomination;
        this.suit = suit;
    }

    private void validateNull(Denomination denomination, Suit suit) {
        if (denomination == null || suit == null) {
            throw new NullPointerException("[ERROR] 카드를 생성할때 숫자와 무늬중 null이 존재합니다.");
        }
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }

    public int getScore() {
        return denomination.getValue();
    }

    public String getDenomination() {
        return denomination.getName();
    }

    public String getSuit() {
        return suit.getName();
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
        return denomination == card.denomination && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, suit);
    }
}
