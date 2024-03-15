package controller;

import static util.InputRetryHelper.inputRetryHelper;
import static view.InputView.inputNames;
import static view.InputView.inputPlayerHitChoice;

import java.util.List;
import model.Choice;
import model.casino.RandomCardShuffleMachine;
import model.participant.Names;
import model.casino.Casino;
import model.participant.dto.DealerFaceUpResult;
import model.participant.dto.PlayerFaceUpResult;
import model.participant.dto.PlayerMatchResult;
import view.OutputView;

public class BlackJackLauncher {

    public void execute() {
        Casino casino = initCasino();
        showInitialFaceUpResults(casino);
        proceedPlayersTurn(casino);
        proceedDealerTurn(casino);
        showFinalFaceUpResults(casino);
        showFinalMatchResults(casino);
    }

    private Casino initCasino() {
        Names names = inputRetryHelper(() -> new Names(inputNames()));
        return new Casino(names, new RandomCardShuffleMachine());
    }

    private void showInitialFaceUpResults(Casino casino) {
        DealerFaceUpResult dealerFaceUpResult = casino.getDealerFaceUpResult();
        List<PlayerFaceUpResult> playerFaceUpResults = casino.getPlayerFaceUpResult();
        OutputView.printInitialCardSetting(dealerFaceUpResult, playerFaceUpResults);
    }

    private void proceedPlayersTurn(Casino casino) {
        while (casino.hasAvailablePlayer()) {
            PlayerFaceUpResult nextPlayerFaceUpInfo = casino.getNextPlayerFaceUpInfo();
            Choice playerChoice = inputRetryHelper(() -> Choice.from(
                    inputPlayerHitChoice(nextPlayerFaceUpInfo.getPartipantNameAsString())));
            casino.distinctPlayerChoice(playerChoice);
            showPlayerChoiceResult(playerChoice, nextPlayerFaceUpInfo);
        }
    }

    private void showPlayerChoiceResult(Choice playerChoice, PlayerFaceUpResult currentPlayerFaceUpInfo) {
        if (playerChoice.isHit() || (!playerChoice.isHit() && currentPlayerFaceUpInfo.cards()
                .size() == 2)) {
            OutputView.printSingleFaceUp(currentPlayerFaceUpInfo);
        }
    }

    private void proceedDealerTurn(Casino casino) {
        while (casino.isDealerHitAllowed()) {
            casino.hitCardToDealer();
            OutputView.alertDealerHitMessage();
        }
    }

    private void showFinalFaceUpResults(Casino casino) {
        List<PlayerFaceUpResult> playerFinalFaceUpResults = casino.getPlayerFaceUpResult();
        DealerFaceUpResult dealerFinalFaceUpResult = casino.getDealerFaceUpResult();
        OutputView.printFinalFaceUpResult(dealerFinalFaceUpResult, playerFinalFaceUpResults);
    }

    private void showFinalMatchResults(Casino casino) {
        OutputView.printScoreResults();
    }
}
