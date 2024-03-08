package controller;

import static util.InputRetryHelper.inputRetryHelper;
import static view.InputView.input;
import static view.InputView.inputNames;

import java.util.List;
import model.Choice;
import model.casino.Casino;
import model.casino.RandomCardShuffleMachine;
import model.dto.DealerScoreResult;
import model.dto.FaceUpResult;
import model.dto.PlayerScoreResult;
import model.participant.Entrant;
import model.participant.Names;
import view.OutputView;

public class Controller {

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
        Names names = inputRetryHelper(() -> new Names(inputNames("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")));
        Entrant entrant = new Entrant(names);
        return new Casino(entrant, new RandomCardShuffleMachine());
    }

    private void showInitialFaceUpResults(Casino casino) {
        FaceUpResult dealerFaceUpResult = casino.getDealerFaceUpResult();
        List<FaceUpResult> playerFaceUpResults = casino.getPlayerFaceUpResult();
        OutputView.printInitialCardSetting(dealerFaceUpResult, playerFaceUpResults);
    }

    private void proceedPlayersTurn(Casino casino) {
        while (casino.hasAvailablePlayer()) {
            FaceUpResult currentPlayerFaceUpInfo = casino.getNextPlayerFaceUpInfo();
            Choice playerChoice = inputRetryHelper(() -> Choice.from(
                    input(currentPlayerFaceUpInfo.getPartipantNameAsString() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")));
            casino.distinctPlayerChoice(playerChoice);
            showPlayerChoiceResult(playerChoice, currentPlayerFaceUpInfo);
        }
    }

    private void showPlayerChoiceResult(Choice playerChoice, FaceUpResult currentPlayerFaceUpInfo) {
        if (playerChoice.isYes() || (!playerChoice.isYes() && currentPlayerFaceUpInfo.cards()
                .size() == 2)) {
            OutputView.printSinglePlayerFaceUp(currentPlayerFaceUpInfo);
        }
    }

    private void proceedDealerTurn(Casino casino) {
        while (casino.isDealerHitAllowed()) {
            casino.hitCardToDealer();
            OutputView.print("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
    }

    private void showFinalFaceUpResults(Casino casino) {
        List<FaceUpResult> playerFinalFaceUpResults = casino.getPlayerFaceUpResult();
        FaceUpResult dealerFinalFaceUpResult = casino.getDealerFaceUpResult();
        OutputView.printFinalFaceUpResult(dealerFinalFaceUpResult, playerFinalFaceUpResults);
    }
}
