package controller;

import static java.util.Collections.*;
import static util.InputRetryHelper.inputRetryHelper;
import static view.InputView.inputChoiceCommand;
import static view.InputView.inputNames;

import java.util.EnumMap;
import java.util.List;
import model.Choice;
import model.casino.CardDispenser;
import model.casino.RandomCardShuffleMachine;
import model.dto.DealerScoreResult;
import model.dto.GameCompletionResult;
import model.dto.PlayerScoreResult;
import model.participant.Names;
import view.OutputView;
import view.Victory;
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
        DealerScoreResult dealerScoreResult = calculateDealerResult();
        List<PlayerScoreResult> playerScoreResults = calculatePlayerResults();
        OutputView.printScoreResults(dealerScoreResult, playerScoreResults);
    }

    private void showInitialFaceUpResults() {
        GameCompletionResult dealerGameCompletionResult = participants.getDealerFaceUpResult();
        List<GameCompletionResult> playerGameCompletionResults = participants.getPlayerFaceUpResults();
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
        List<GameCompletionResult> playerFinalGameCompletionResults = participants.getPlayerFaceUpResults();
        GameCompletionResult dealerFinalGameCompletionResult = participants.getDealerFaceUpResult();
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

    private List<PlayerScoreResult> calculatePlayerResults() {
        int dealerHand = participants.getDealerFaceUpResult()
                .hand();
        return participants.getPlayerFaceUpResults()
                .stream()
                .map(result -> new PlayerScoreResult(result.name(), Victory.of(result.hand(), dealerHand)))
                .toList();
    }

    private DealerScoreResult calculateDealerResult() {
        int dealerHand = participants.getDealerFaceUpResult().hand();
        EnumMap<Victory, Integer> dealerScoreBoard = new EnumMap<>(Victory.class);
        List<Victory> dealerScores = participants.getPlayerFaceUpResults()
                .stream()
                .map(player -> Victory.of(dealerHand, player.hand()))
                .toList();
        dealerScoreBoard.put(Victory.WIN, frequency(dealerScores, Victory.WIN));
        dealerScoreBoard.put(Victory.DRAW, frequency(dealerScores, Victory.DRAW));
        dealerScoreBoard.put(Victory.LOSE, frequency(dealerScores, Victory.LOSE));
        return new DealerScoreResult(dealerScoreBoard);
    }

}
