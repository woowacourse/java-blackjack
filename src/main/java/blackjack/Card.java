package blackjack;

public class Card {
    private final String cardInfo;

    public Card(String card) {
        this.cardInfo = card;
    }

    public int getRank() {
        return number(cardInfo.substring(0, 1));
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
}
