package controller;

import static model.money.BetTable.DEALER_NAME;
import static model.money.BetTable.getInstance;
import static util.InputRetryHelper.inputRetryHelper;
import static view.InputView.inputBetAmount;
import static view.InputView.inputChoiceCommand;
import static view.InputView.inputNames;

import java.util.List;
import model.Choice;
import model.casino.CardDispenser;
import model.casino.DividendPolicyFactory;
import model.casino.RandomCardShuffleMachine;
import model.dto.OddsResult;
import model.dto.GameCompletionResult;
import model.money.BetTable;
import model.money.DividendPolicy;
import model.money.Money;
import model.participant.Name;
import model.participant.Names;
import model.participant.Participant;
import view.OutputView;
import model.participant.Participants;

public class Casino {
    private final Participants participants;
    private final CardDispenser cardDispenser;

    public Casino() {
        Names names = inputRetryHelper(() -> new Names(inputNames()));
        this.participants = new Participants(names);
        this.cardDispenser = new CardDispenser(new RandomCardShuffleMachine());
    }

    public void execute() {
        insertAllBetAmount();
        hitCardTwice();
        showInitialFaceUpResults();
        proceedPlayersTurn();
        proceedDealerTurn();
        showFinalFaceUpResults();
        distributeAllMoney();
        showFinalOdds();
    }

    private void insertAllBetAmount() {
        participants.getPlayerCompletionResults().forEach(this::insertBetAmount);
    }

    private void insertBetAmount(GameCompletionResult result) {
        Money money = inputRetryHelper(() ->
                Money.createBettingAmount(inputBetAmount(result.getPartipantNameAsString())));

        BetTable.getInstance().add(result.name(), money);
    }

    private void showInitialFaceUpResults() {
        GameCompletionResult dealerGameCompletionResult = participants.getDealerCompletionResult();
        List<GameCompletionResult> playerGameCompletionResults = participants.getPlayerCompletionResults();
        OutputView.printInitialCardSetting(dealerGameCompletionResult, playerGameCompletionResults);
    }

    private void proceedPlayersTurn() {
        while (participants.hasAvailablePlayer()) {
            GameCompletionResult currentPlayerCompletionResult = participants.getNextAvailablePlayerName();
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

    private void distributeAllMoney() {
        getInstance().findAllParticipantNames()
                .stream()
                .filter(name -> !name.equals(DEALER_NAME))
                .forEach(this::distributeMoney);
    }

    private void distributeMoney(Name name) {
        Participant dealer = participants.findParticipantByName(DEALER_NAME);
        Participant player = participants.findParticipantByName(name);
        DividendPolicy policyInProceed = DividendPolicyFactory.findPolicy(dealer, player);
        BetTable.getInstance().remittanceByPolicy(name, policyInProceed);
    }

    private void showFinalOdds() {
        OddsResult dealerOddsResult = BetTable.getInstance().getDealerFinalOddsResult();
        List<OddsResult> playerOddsResults = getInstance().getPlayerFinalOddsResults();

        OutputView.printFinalGameResult(dealerOddsResult, playerOddsResults);
    }

}
