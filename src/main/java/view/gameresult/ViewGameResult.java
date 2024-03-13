package view.gameresult;

import domain.blackjack.GameResult;

enum ViewGameResult {
    WIN("승"),
    LOSE("패"),
    TIE("무");
    private final String output;

    static ViewGameResult of(GameResult gameResult) {
        return valueOf(gameResult.name());
    }

    ViewGameResult(String output) {
        this.output = output;
    }

    String getOutput() {
        return output;
    }
}
