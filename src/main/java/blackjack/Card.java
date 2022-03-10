package blackjack;

public class Card {
    private final String cardInfo;
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        cardInfo = null;
        this.rank = rank;
        this.suit = suit;
    }

    public Card(String card) {
        this.cardInfo = card;
        this.rank = null;
        this.suit = null;
    }

    public int getRank() {
        if (rank == null) {
            return number(cardInfo.substring(0, 1));
        }
        return rank.hard();
    }

    private int number(String value) {
        if (value.equals("J") || value.equals("Q") || value.equals("K")) {
            return 10;
        }
        if (value.equals("A")) {
            return 1;
        }
        return Integer.parseInt(value);
    }

    public boolean isAce() {
        return getRank() == 1;
    }

    @Override
    public String toString() {
        return "Card{" +
            "cardInfo='" + cardInfo + '\'' +
            '}';
    }
}
