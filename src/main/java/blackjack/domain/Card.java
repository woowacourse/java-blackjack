package blackjack.domain;

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
            throw new NullPointerException("카드를 생성할때 숫자와 무늬중 null이 존재합니다.");
        }
    }
}
