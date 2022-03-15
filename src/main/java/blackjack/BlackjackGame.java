package blackjack;

import blackjack.domain.BlackjackBoard;
import blackjack.domain.HitCommand;
import blackjack.domain.participant.Participant;
import blackjack.dto.OutComeResult;
import blackjack.dto.ParticipantCards;
import blackjack.dto.ParticipantScoreResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    private final BlackjackBoard blackjackBoard;

    public BlackjackGame() {
        this.blackjackBoard = createBlackjackBoard();
    }

    private BlackjackBoard createBlackjackBoard() {
        final List<String> playerNames = InputView.inputPlayerNames();
        return BlackjackBoard.createGame(playerNames);
    }

    public void run() {
        printFirstDrawCard();
        runAllPlayerTurn();
        printAllResults();
    }

    private void printFirstDrawCard() {
        OutputView.showPlayersFirstCards(
                ParticipantCards.toParticipantFirstCards(blackjackBoard.getDealer()),
                blackjackBoard.getPlayers().stream()
                        .map(ParticipantCards::toParticipantFirstCards)
                        .collect(Collectors.toList())
        );
    }

    private void runAllPlayerTurn() {
        runPlayerTurn();
        runDealerTurn();
    }

    private void runPlayerTurn() {
        if (blackjackBoard.isPlayersTurnEnd()) {
            return;
        }
        final HitCommand hitCommand = inputHitCommand();
        final ParticipantCards currentPlayerCards = takeCurrentPlayerTurn(hitCommand);
        OutputView.printPlayerCards(currentPlayerCards);
        runPlayerTurn();
    }

    private ParticipantCards takeCurrentPlayerTurn(final HitCommand hitCommand) {
        return ParticipantCards.toParticipantCards(blackjackBoard.takeCurrentPlayerTurn(hitCommand));
    }

    private HitCommand inputHitCommand() {
        return HitCommand.from(InputView.inputHitCommand(blackjackBoard.getCurrentTurnPlayerName()));
    }

    private void runDealerTurn() {
        if (blackjackBoard.isDealerTurnEnd()) {
            return;
        }
        blackjackBoard.hitDealer();
        OutputView.printDealerHit();
        runDealerTurn();
    }

    private void printAllResults() {
        OutputView.printPlayerScoreResults(toParticipantScoreResults(blackjackBoard.getAllParticipants()));
        OutputView.printAllOutcomeResult(OutComeResult.from(blackjackBoard.calculateAllResults()));
    }

    private List<ParticipantScoreResult> toParticipantScoreResults(final List<Participant> participants) {
        return participants.stream()
                .map(ParticipantScoreResult::from)
                .collect(Collectors.toList());
    }
}
