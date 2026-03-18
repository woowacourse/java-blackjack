package blackjack;

import blackjack.domain.Participants;
import blackjack.dto.ParticipantResult;
import blackjack.dto.PlayerNicknamesResult;
import blackjack.dto.TotalGameResult;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackGameView {

    private final OutputView outputView;

    public BlackjackGameView(OutputView outputView) {
        this.outputView = outputView;
    }

    public void printInitialState(Participants participants) {
        PlayerNicknamesResult playerNicknamesResult = PlayerNicknamesResult.from(participants);
        List<ParticipantResult> participantResults = participants.getInitialResult();
        outputView.printInitialSetUp(playerNicknamesResult);
        outputView.printInitialResult(participantResults);
    }

    public void printFinalResult(Participants participants) {
        List<ParticipantResult> participantResult = participants.getGameResult();
        TotalGameResult gameResult = participants.getWinningResult();
        outputView.printGameResult(participantResult);
        outputView.printTotalProfitResult(gameResult);
    }
}
