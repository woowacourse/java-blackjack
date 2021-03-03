package blackjack.domain.scoreboard;

public enum WinOrLose {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String character;

    WinOrLose(String character){
        this.character = character;
    }
}
