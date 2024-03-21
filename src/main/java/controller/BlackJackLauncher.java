package controller;

import static util.InputRetryHelper.inputRetryHelper;
import static view.InputView.inputBettingAmount;
import static view.InputView.inputNames;
import static view.InputView.inputPlayerHitChoice;

import controller.dto.BettingInfo;
import controller.dto.BettingInfos;
import controller.dto.DealerFaceUpResultInfo;
import controller.dto.PlayerFaceUpResult;
import controller.dto.PlayerFaceUpResultInfo;
import java.util.List;
import model.casino.Casino;
import model.casino.RandomCardShuffleMachine;
import model.participant.Names;
import util.ResultMapper;
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
        DealerFaceUpResultInfo dealerFaceUpResultInfo = ResultMapper.fromDealerFaceUpResult(
                casino.getDealerFaceUpResult());
        List<PlayerFaceUpResultInfo> playerFaceUpResults = ResultMapper.fromPlayerFaceUpResult(
                casino.getPlayerFaceUpResult());
        OutputView.printInitialCardSetting(dealerFaceUpResultInfo, playerFaceUpResults);
    }

    private void proceedPlayersTurn(Casino casino) {
        while (casino.hasAvailablePlayer()) {
            PlayerFaceUpResult nextPlayerFaceUpReuslt = casino.getNextPlayerFaceUpInfo();
            boolean playerChoice = inputRetryHelper(
                    () -> inputPlayerHitChoice(nextPlayerFaceUpReuslt.getPartipantNameAsString()));
            casino.distinctPlayerChoice(playerChoice);
            PlayerFaceUpResultInfo nextPlayerFaceUpInfo = ResultMapper.fromPlayerFaceUpResult(nextPlayerFaceUpReuslt);
            showPlayerChoiceResult(playerChoice, nextPlayerFaceUpInfo);
        }
    }

    private void showPlayerChoiceResult(boolean playerChoice, PlayerFaceUpResultInfo currentPlayerFaceUpInfo) {
        if (playerChoice || currentPlayerFaceUpInfo.cardFaces()
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
        List<PlayerFaceUpResultInfo> playerFinalFaceUpResultInfos = ResultMapper.fromPlayerFaceUpResult(
                casino.getPlayerFaceUpResult());
        DealerFaceUpResultInfo dealerFinalFaceUpResultInfo = ResultMapper.fromDealerFaceUpResult(
                casino.getDealerFaceUpResult());
        OutputView.printFinalFaceUpResult(dealerFinalFaceUpResultInfo, playerFinalFaceUpResultInfos);
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
