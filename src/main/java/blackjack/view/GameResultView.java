package blackjack.view;

import blackjack.domain.GameResult;

public enum GameResultView {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String message;

    GameResultView(final String message) {
        this.message = message;
    }

    public static String getShapeMessage(final GameResult gameResult) {
        return GameResultView.valueOf(gameResult.name()).message;
    }
}
