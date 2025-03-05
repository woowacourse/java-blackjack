package model;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    private final String resultMeaning;

    GameResult(String resultMeaning) {
        this.resultMeaning = resultMeaning;
    }

}
