package domain;

public enum Outcome {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String name;

    Outcome(String name) {
        this.name = name;
    }

    public static Outcome decideWinner(Score playerScore, Score dealerScore) {
        if (playerScore.isBust()) {
            return LOSE;
        }
        if (dealerScore.isBust()) {
            return WIN;
        }

        if (playerScore.getGameScore() > dealerScore.getGameScore()) {
            return WIN;
        }
        if (playerScore.getGameScore() < dealerScore.getGameScore()) {
            return LOSE;
        }
        return DRAW;
    }

    public String getName() {
        return name;
    }

    public Outcome opposite() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}