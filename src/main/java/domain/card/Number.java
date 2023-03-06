package domain.card;

public enum Number {
    ACE(11, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    KING(10, "K"),
    QUEEN(10, "Q"),
    JACK(10, "J");

    private final int score;
    private final String symbol;
    
    Number(int score, String symbol) {
        this.score = score;
        this.symbol = symbol;
    }

    public boolean isAce() {
        return this == ACE;
    }

    public int getScore() {
        return score;
    }
    
    public String getSymbol() {
        return symbol;
    }
}
