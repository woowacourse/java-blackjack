package console;

import model.participant.Participants;
import view.GameResultView;

public final class GameResultConsole extends Console {
    private final GameResultView gameResultView = new GameResultView();

    public void getFinalScores(Participants participants) {
        display(gameResultView.getFinalScores(participants));
    }
}
