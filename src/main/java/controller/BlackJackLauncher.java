package controller;

import static util.InputRetryHelper.inputRetryHelper;
import static view.InputView.inputChoiceCommand;
import static view.InputView.inputNames;

import java.util.List;
import model.Choice;
import model.casino.Casino;
import model.casino.RandomCardShuffleMachine;
import model.dto.DealerScoreResult;
import model.dto.GameCompletionResult;
import model.dto.PlayerScoreResult;
import model.participant.Participants;
import model.participant.Names;
import view.OutputView;

public class BlackJackLauncher {

    public void execute() {
        Casino casino = initCasino();
        casino.initializeGame();
        showInitialFaceUpResults(casino);
        proceedPlayersTurn(casino);
        proceedDealerTurn(casino);
        showFinalFaceUpResults(casino);
        DealerScoreResult dealerScoreResult = casino.calculateDealerResult();
        List<PlayerScoreResult> playerScoreResults = casino.calculatePlayerResults();
        OutputView.printScoreResults(dealerScoreResult, playerScoreResults);
    }

    private Casino initCasino() {
        Names names = inputRetryHelper(() -> new Names(inputNames()));
        Participants participants = new Participants(names);
        return new Casino(participants, new RandomCardShuffleMachine());
    }

    private void showInitialFaceUpResults(Casino casino) {
        GameCompletionResult dealerGameCompletionResult = casino.getDealerFaceUpResult();
        List<GameCompletionResult> playerGameCompletionResults = casino.getPlayerFaceUpResult();
        OutputView.printInitialCardSetting(dealerGameCompletionResult, playerGameCompletionResults);
    }

    private void proceedPlayersTurn(Casino casino) {
        while (casino.hasAvailablePlayer()) {
            GameCompletionResult currentPlayerCompletionResult = casino.getNextPlayerFaceUpInfo();
            Choice playerChoice = inputRetryHelper(() -> Choice.from(
                    inputChoiceCommand(currentPlayerCompletionResult)));
            casino.distinctPlayerChoice(playerChoice);
            showPlayerChoiceResult(playerChoice, currentPlayerCompletionResult);
        }
    }

    private void showPlayerChoiceResult(Choice playerChoice, GameCompletionResult currentPlayerFaceUpInfo) {
        if (playerChoice.isYes() || (!playerChoice.isYes() && currentPlayerFaceUpInfo.cards()
                .size() == 2)) {
            OutputView.printSinglePlayerFaceUp(currentPlayerFaceUpInfo);
        }
    }

    private void proceedDealerTurn(Casino casino) {
        while (casino.isDealerHitAllowed()) {
            casino.hitCardToDealer();
            OutputView.printRetryMessage();
        }
    }

    private void showFinalFaceUpResults(Casino casino) {
        List<GameCompletionResult> playerFinalGameCompletionResults = casino.getPlayerFaceUpResult();
        GameCompletionResult dealerFinalGameCompletionResult = casino.getDealerFaceUpResult();
        OutputView.printFinalFaceUpResult(dealerFinalGameCompletionResult, playerFinalGameCompletionResults);
    }
}
