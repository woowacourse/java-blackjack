package blackjack.dto;

public class CardDto {
    private final String suit;
    private final String value;

    public CardDto(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }
}
