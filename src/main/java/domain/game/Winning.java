package domain.game;

public enum Winning {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    public static final int BLACK_JACK = 21;

    private final String name;

    Winning(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static boolean isBust(int score) {
        return (score > BLACK_JACK);
    }

    public static Winning determine(int playerScore, int dealerScore) {
        if(isBust(playerScore)){
            return LOSE;
        }
        if(isBust(dealerScore)){
            return WIN;
        }

        return determinePlayerWinning(playerScore, dealerScore);
    }

    private static Winning determinePlayerWinning(int playerScore, int dealerScore) {
        if(playerScore > dealerScore){
            return WIN;
        }
        if(playerScore < dealerScore){
            return LOSE;
        }
        return DRAW;
    }
}
