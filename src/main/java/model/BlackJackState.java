package model;

public enum BlackJackState {
    STAND(1),
    BUST(-1),
    BLACKJACK(1.5),
    ;

    private final double battingRatio;

    BlackJackState(double battingRatio) {
        this.battingRatio = battingRatio;
    }

    public static BlackJackState createBlackJackState(int score, int cardsCount) {
        if (score == 21 && cardsCount == 2) {
            return BLACKJACK;
        }
        if (score > 21) {
            return BUST;
        }
        return STAND;
    }

    public int calculateBattingRatio(int score) {
        return (int) (score * battingRatio);
    }
}
