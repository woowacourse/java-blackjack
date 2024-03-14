package controller;

import static util.InputRetryHelper.inputRetryHelper;
import static view.InputView.inputNames;
import static view.InputView.inputPlayerHitChoice;

import java.util.List;
import model.Choice;
import model.casino.RandomCardShuffleMachine;
import model.participant.Names;
import service.CasinoService;
import service.dto.DealerScoreResult;
import service.dto.FaceUpResult;
import service.dto.PlayerMatchResult;
import view.OutputView;

public class BlackJackLauncher {

    public void execute() {
        CasinoService casinoService = initCasino();
        casinoService.initializeGame();
        showInitialFaceUpResults(casinoService);
        proceedPlayersTurn(casinoService);
        proceedDealerTurn(casinoService);
        showFinalFaceUpResults(casinoService);
        showFinalMatchResults(casinoService);
    }

    private CasinoService initCasino() {
        Names names = inputRetryHelper(() -> new Names(inputNames("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")));
        return new CasinoService(names, new RandomCardShuffleMachine());
    }

    private void showInitialFaceUpResults(CasinoService casinoService) {
        FaceUpResult dealerFaceUpResult = casinoService.getDealerFaceUpResult();
        List<FaceUpResult> playerFaceUpResults = casinoService.getPlayerFaceUpResult();
        OutputView.printInitialCardSetting(dealerFaceUpResult, playerFaceUpResults);
    }

    private void proceedPlayersTurn(CasinoService casinoService) {
        while (casinoService.hasAvailablePlayer()) {
            FaceUpResult currentPlayerFaceUpInfo = casinoService.getNextPlayerFaceUpInfo();
            Choice playerChoice = inputRetryHelper(() -> Choice.from(
                    inputPlayerHitChoice(currentPlayerFaceUpInfo.getPartipantNameAsString())));
            casinoService.distinctPlayerChoice(playerChoice);
            showPlayerChoiceResult(playerChoice, currentPlayerFaceUpInfo);
        }
    }

    private void showPlayerChoiceResult(Choice playerChoice, FaceUpResult currentPlayerFaceUpInfo) {
        if (playerChoice.isHit() || (!playerChoice.isHit() && currentPlayerFaceUpInfo.cards()
                .size() == 2)) {
            OutputView.printSingleFaceUp(currentPlayerFaceUpInfo);
        }
    }

    private void proceedDealerTurn(CasinoService casinoService) {
        while (casinoService.isDealerHitAllowed()) {
            casinoService.hitCardToDealer();
            OutputView.printMessage("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
    }

    private void showFinalFaceUpResults(CasinoService casinoService) {
        List<FaceUpResult> playerFinalFaceUpResults = casinoService.getPlayerFaceUpResult();
        FaceUpResult dealerFinalFaceUpResult = casinoService.getDealerFaceUpResult();
        OutputView.printFinalFaceUpResult(dealerFinalFaceUpResult, playerFinalFaceUpResults);
    }

    private void showFinalMatchResults(CasinoService casinoService) {
        DealerScoreResult dealerScoreResult = casinoService.calculateDealerMatchResult();
        List<PlayerMatchResult> playerMatchResults = casinoService.calculatePlayerMatchResults();
        OutputView.printScoreResults(dealerScoreResult, playerMatchResults);
    }
}
