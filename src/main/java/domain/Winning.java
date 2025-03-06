package domain;

public enum Winning {
    WIN,
    DRAW,
    LOSE,
    ;

    public static final int BLACK_JACK = 21;

    public static Winning determine(int playerScore, int dealerScore) {
        if(isBust(playerScore)){
            return LOSE;
        }
        if(isBust(dealerScore)){
            return WIN;
        }

        if(playerScore > dealerScore){
            return WIN;
        }
        if(playerScore < dealerScore){
            return LOSE;
        }
        return DRAW;
    }

    public static boolean isBust(int score) {
        return (score > BLACK_JACK);
    }
}
