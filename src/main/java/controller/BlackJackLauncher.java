package controller;

import static util.InputRetryHelper.inputRetryHelper;
import static view.InputView.inputBettingAmount;
import static view.InputView.inputNames;
import static view.InputView.inputPlayerHitChoice;

import controller.dto.BettingInfo;
import controller.dto.BettingInfos;
import java.util.List;
import model.casino.Casino;
import model.casino.RandomCardShuffleMachine;
import model.participant.Names;
import model.participant.dto.DealerFaceUpResult;
import model.participant.dto.PlayerFaceUpResult;
import view.OutputView;

public class BlackJackLauncher {

    public void execute() {
        Casino casino = initCasino();
        showInitialFaceUpResults(casino);
        proceedPlayersTurn(casino);
        proceedDealerTurn(casino);
        showFinalFaceUpResults(casino);
        casino.aggregateBettingResult();
        showFinalMatchResults(casino);
    }

    private Casino initCasino() {
        Names names = inputRetryHelper(() -> new Names(inputNames()));
        BettingInfos bettingInfos = BettingInfos.from(names.getPlayerNames()
                .stream()
                .map(name -> new BettingInfo(name, inputRetryHelper(() -> inputBettingAmount(name.getValue()))))
                .toList());
        return new Casino(bettingInfos, new RandomCardShuffleMachine());
    }

    private void showInitialFaceUpResults(Casino casino) {
        DealerFaceUpResult dealerFaceUpResult = casino.getDealerFaceUpResult();
        List<PlayerFaceUpResult> playerFaceUpResults = casino.getPlayerFaceUpResult();
        OutputView.printInitialCardSetting(dealerFaceUpResult, playerFaceUpResults);
    }

    private void proceedPlayersTurn(Casino casino) {
        while (casino.hasAvailablePlayer()) {
            PlayerFaceUpResult nextPlayerFaceUpInfo = casino.getNextPlayerFaceUpInfo();
            boolean playerChoice = inputRetryHelper(
                    () -> inputPlayerHitChoice(nextPlayerFaceUpInfo.getPartipantNameAsString()));
            casino.distinctPlayerChoice(playerChoice);
            showPlayerChoiceResult(playerChoice, nextPlayerFaceUpInfo);
        }
    }

    private void showPlayerChoiceResult(boolean playerChoice, PlayerFaceUpResult currentPlayerFaceUpInfo) {
        if (playerChoice || currentPlayerFaceUpInfo.cards()
                .size() == 2) {
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
        BettingInfos playerBettingResults = casino.calculatePlayerBettingResults();
        long dealerEarnings = casino.calculateDealerEarningResults();
        OutputView.printDealerEarningResults(dealerEarnings);
        playerBettingResults.getBettingInfos()
                .forEach(bettingInfo -> OutputView.printPlayerBettingResult(bettingInfo.getNameAsString(),
                        bettingInfo.bettingAmount()));
    }
}
