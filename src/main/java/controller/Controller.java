package controller;

import static util.InputRetryHelper.inputRetryHelper;
import static view.InputView.input;
import static view.InputView.inputNames;

import java.util.List;
import model.Choice;
import model.casino.Casino;
import model.casino.RandomCardShuffleMachine;
import model.dto.IndividualFaceUpResult;
import model.participant.Entrant;
import model.participant.Names;
import view.OutputView;

public class Controller {

    public void execute() {
        Names names = inputRetryHelper(() -> new Names(inputNames("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")));
        Entrant entrant = new Entrant(names);
        Casino casino = new Casino(entrant, new RandomCardShuffleMachine());
        casino.initializeGame();
        IndividualFaceUpResult dealerFaceUpResult = casino.getDealerFaceUpResult();
        List<IndividualFaceUpResult> playerFaceUpResults = casino.getPlayerFaceUpResult();
        OutputView.printInitialCardSetting(dealerFaceUpResult, playerFaceUpResults);
        while (casino.hasAvailablePlayer()) {
            IndividualFaceUpResult currentPlayerFaceUpInfo = casino.getNextPlayerFaceUpInfo();
            Choice playerChoice = inputRetryHelper(() -> Choice.from(
                    input(currentPlayerFaceUpInfo.getPartipantNameAsString() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")));
            casino.distinctPlayerChoice(playerChoice);
            showPlayerChoiceResult(playerChoice, currentPlayerFaceUpInfo);
        }

        while(casino.isDealerHitAllowed()){
            casino.hitCardToDealer();
            OutputView.print("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }



    }

    private void showPlayerChoiceResult(Choice playerChoice, IndividualFaceUpResult currentPlayerFaceUpInfo) {
        if (playerChoice.isYes() || (!playerChoice.isYes() && currentPlayerFaceUpInfo.cards()
                .size() == 2)) {
            OutputView.printPlayersFaceUp(currentPlayerFaceUpInfo);
        }
    }
}
