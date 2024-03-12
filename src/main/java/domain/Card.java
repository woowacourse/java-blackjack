package domain;

public class Card {

    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public boolean isAceCard() {
        return this.rank.isAce();
    }

    public int getLetterValue() {
        return rank.getValue();
    }

    public String getLetterText() {
        return rank.getText();
    }

    public String getMark() {
        return suit.getName();
    }
}
