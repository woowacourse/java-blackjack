package console;

import participant.Participant;
import participant.Participants;
import view.GameResultView;

public final class GameResultConsole extends Console {
    private final GameResultView gameResultView = new GameResultView();

    public void getFinalScores(final Participants participants) {
        display(gameResultView.getFinalScores(participants)); // TODO 로직들 View -> Console로 다 이동시키기
    }

    public void getFinalProfits(final Participants participants) {
        display(gameResultView.getFinalProfitHeader());
        for (Participant participant : participants.getParticipants()) {
            String finalProfitView = gameResultView.getFinalProfit(participant.getName(), participant.getProfit());
            display(finalProfitView);
        }
    }
}
