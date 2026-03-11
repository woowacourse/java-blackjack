package domain.game_result;

enum WinningCondition {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String description;

    WinningCondition(String description) {
        this.description = description;
    }

    String description() {
        return description;
    }
}
