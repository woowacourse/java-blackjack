package blackjack.domain.dto;

public class CardDto {

    private final String denomination;
    private final String suit;

    public CardDto(String denomination, String suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public String getDenomination() {
        return denomination;
    }

    public String getSuit() {
        return suit;
    }
}
