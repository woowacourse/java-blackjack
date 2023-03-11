package controller;

import java.util.List;

import domain.AdditionalDrawStatus;
import domain.BlackJackGame;
import domain.betting.BettingManager;
import domain.betting.BettingMoney;
import domain.participant.Player;
import util.Constants;
import view.InputValidator;
import view.InputView;
import view.OutputView;
import view.ResultView;

public class BlackJackController {

    public void run() {
        BlackJackGame blackJackGame = generateBlackJackGame();
        BettingManager bettingManager = new BettingManager();
        saveBettingMoney(blackJackGame, bettingManager);
        initDistributeCards(blackJackGame);
        additionalCardDraw(blackJackGame);
        printFinalCardResult(blackJackGame);
        calculateFinalProfit(blackJackGame, bettingManager);
        printFinalProfit(bettingManager);
    }

    private BlackJackGame generateBlackJackGame() {
        List<String> playerNames = initPlayerNames();
        return new BlackJackGame(playerNames);
    }

    private List<String> initPlayerNames() {
        try {
            OutputView.printInputPlayerNameMessage();
            List<String> playerNames = InputView.inputPlayerNames();
            return playerNames;
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return initPlayerNames();
        }
    }

    private void saveBettingMoney(BlackJackGame blackJackGame, BettingManager bettingManager) {
        List<String> playerNames = blackJackGame.getPlayerNames();
        for (String playerName : playerNames) {
            BettingMoney bettingMoney = generateBettingMoney(playerName);
            Player findPlayer = blackJackGame.findPlayerByPlayerName(playerName);
            bettingManager.savePlayerBettingMoney(findPlayer, bettingMoney);
        }
    }

    private void initDistributeCards(BlackJackGame blackJackGame) {
        List<String> playerNames = blackJackGame.getPlayerNames();
        ResultView.printInitMessage(playerNames);
        ResultView.printParticipantResult(Constants.DEALER_NAME, blackJackGame.findCardNamesByParticipantName(Constants.DEALER_NAME));
        for (String playerName : playerNames) {
            List<String> findCardNames = blackJackGame.findCardNamesByParticipantName(playerName);
            ResultView.printParticipantResult(playerName, findCardNames);
        }
    }

    private void additionalCardDraw(BlackJackGame blackJackGame) {
        askPlayerAdditionalCardDrawStep(blackJackGame);
        dealerAdditionalCardDrawStep(blackJackGame);
    }

    private BettingMoney generateBettingMoney(String playerName) {
        try {
            OutputView.printInputBettingMoney(playerName);
            String bettingMoney = InputView.inputBettingMoney();
            InputValidator.validateBettingMoney(bettingMoney);
            return new BettingMoney(Integer.parseInt(bettingMoney));
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return generateBettingMoney(playerName);
        }
    }

    private void askPlayerAdditionalCardDrawStep(BlackJackGame blackJackGame) {
        List<String> playerNames = blackJackGame.getPlayerNames();
        for (String playerName : playerNames) {
            askPlayerAdditionalCardDraw(blackJackGame, playerName);
        }
    }

    private void askPlayerAdditionalCardDraw(BlackJackGame blackJackGame, String playerName) {
        AdditionalDrawStatus additionalDrawStatus = AdditionalDrawStatus.DRAW;
        while (AdditionalDrawStatus.isDrawable(additionalDrawStatus) && blackJackGame.canPlayerDrawCard(playerName)) {
            OutputView.printInputReceiveYesOrNotMessage(playerName);
            String receiveOrNot = InputView.inputReceiveOrNot();
            additionalDrawStatus = blackJackGame.distributePlayerCardOrPass(playerName, receiveOrNot);
            ResultView.printParticipantResult(playerName, blackJackGame.findCardNamesByParticipantName(playerName));
        }
    }

    private void dealerAdditionalCardDrawStep(BlackJackGame blackJackGame) {
        while (blackJackGame.canDealerDrawCard()) {
            OutputView.printDealerReceivedMessage();
            blackJackGame.distributeDealerCard();
        }
    }

    private void printFinalCardResult(BlackJackGame blackJackGame) {
        ResultView.printParticipantFinalResult(Constants.DEALER_NAME, blackJackGame.findCardNamesByParticipantName(Constants.DEALER_NAME), blackJackGame.getDealerCardValueSum());
        List<String> playerNames = blackJackGame.getPlayerNames();
        for (String playerName : playerNames) {
            List<String> findCardNames = blackJackGame.findCardNamesByParticipantName(playerName);
            ResultView.printParticipantFinalResult(playerName, findCardNames, blackJackGame.findPlayerCardValueSumByPlayerName(playerName));
        }
    }

    private void calculateFinalProfit(BlackJackGame blackJackGame, BettingManager bettingManager) {
        bettingManager.calculateParticipantFinalProfit(blackJackGame.getRawPlayers(), blackJackGame.getDealer());
    }

    private void printFinalProfit(BettingManager bettingManager) {
        ResultView.printFinalProfit(bettingManager.calculateDealerFinalProfit(), bettingManager.getFinalProfitByParticipantForPrint());
    }
}
