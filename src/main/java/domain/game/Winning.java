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

    public String getName() {
        return name;
    }
}
