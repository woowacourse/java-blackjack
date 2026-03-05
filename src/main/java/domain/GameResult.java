package domain;

public enum
GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    private String description;

    GameResult(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static GameResult judge(int standardScore, int myScore) {
        if ((standardScore < myScore && myScore <= 21)
                || (standardScore > 21 && myScore <= 21)) {
            return WIN;
        }
        if (standardScore > 21
                || (myScore <= 21 && standardScore == myScore)) {
            return DRAW;
        }
        return LOSE;
    }
}
