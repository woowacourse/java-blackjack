package controller;

import static util.InputRetryHelper.inputRetryHelper;
import static view.InputView.inputChoiceCommand;
import static view.InputView.inputNames;

import java.util.List;
import model.Choice;
import model.casino.CardDispenser;
import model.casino.RandomCardShuffleMachine;
import model.dto.GameCompletionResult;
import model.participant.Names;
import view.OutputView;
import model.participant.Participants;

public class Casino {
    private final Participants participants;
    private final CardDispenser cardDispenser;

    public Casino() {
        Names names = inputRetryHelper(() -> new Names(inputNames()));
        this.participants = new Participants(names);
        this.cardDispenser =  new CardDispenser(new RandomCardShuffleMachine());
    }

    public void execute() {
        hitCardTwice();
        showInitialFaceUpResults();
        proceedPlayersTurn();
        proceedDealerTurn();
        showFinalFaceUpResults();
    }

    private void showInitialFaceUpResults() {
        GameCompletionResult dealerGameCompletionResult = participants.getDealerCompletionResult();
        List<GameCompletionResult> playerGameCompletionResults = participants.getPlayerCompletionResults();
        OutputView.printInitialCardSetting(dealerGameCompletionResult, playerGameCompletionResults);
    }

    private void proceedPlayersTurn() {
        while (participants.hasAvailablePlayer()) {
            GameCompletionResult currentPlayerCompletionResult =  participants.getNextAvailablePlayerName();
            Choice playerChoice = inputRetryHelper(() -> Choice.from(
                    inputChoiceCommand(currentPlayerCompletionResult)));
            distinctPlayerChoice(playerChoice);
            showPlayerChoiceResult(playerChoice, currentPlayerCompletionResult);
        }
    }

    private void showPlayerChoiceResult(Choice playerChoice, GameCompletionResult currentPlayerFaceUpInfo) {
        if (playerChoice.isYes() || currentPlayerFaceUpInfo.cards().size() == 2) {
            OutputView.printSinglePlayerFaceUp(currentPlayerFaceUpInfo);
        }
    }

    private void proceedDealerTurn() {
        while (participants.canHitDealer()) {
            participants.hitDealer(cardDispenser.dispenseCard());
            OutputView.printRetryMessage();
        }
    }

    private void showFinalFaceUpResults() {
        List<GameCompletionResult> playerFinalGameCompletionResults = participants.getPlayerCompletionResults();
        GameCompletionResult dealerFinalGameCompletionResult = participants.getDealerCompletionResult();
        OutputView.printFinalFaceUpResult(dealerFinalGameCompletionResult, playerFinalGameCompletionResults);
    }

    private void hitCardTwice() {
        int playerSize = participants.getPlayerSize();
        for (int i = 0; i < playerSize; i++) {
            participants.hitAndMoveToNextPlayer(cardDispenser.dispenseCard());
            participants.hitAndMoveToNextPlayer(cardDispenser.dispenseCard());
        }
        participants.hitDealer(cardDispenser.dispenseCard());
        participants.hitDealer(cardDispenser.dispenseCard());
    }

    private void distinctPlayerChoice(Choice choice) {
        if (choice.isYes()) {
            participants.hitPlayer(cardDispenser.dispenseCard());
            return;
        }
        participants.turnOverPlayer();
    }

}
