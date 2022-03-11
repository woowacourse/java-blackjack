package blackjack.model.dto;

public class CardDTO {

    private final String rank;
    private final String suit;

    public CardDTO(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }
}
