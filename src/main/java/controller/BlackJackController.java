package controller;

import java.util.List;

import domain.AdditionalDrawStatus;
import domain.BlackJackGame;
import domain.betting.BettingManager;
import domain.betting.BettingMoney;
import domain.participant.ParticipantFinalResultDto;
import domain.participant.ParticipantResultDto;
import domain.participant.Player;
import util.Constants;
import view.InputView;
import view.OutputView;
import view.ResultView;

public class BlackJackController {

    public void run() {
        BlackJackGame blackJackGame = generateBlackJackGame();
        BettingManager bettingManager = new BettingManager();
        startGameStep(blackJackGame, bettingManager);
        additionalCardDrawStep(blackJackGame);
        printResultStep(blackJackGame, bettingManager);
    }

    private void startGameStep(BlackJackGame blackJackGame, BettingManager bettingManager) {
        saveBettingMoney(blackJackGame, bettingManager);
        initDistributeCards(blackJackGame);
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
        ParticipantResultDto dealerResultDto = blackJackGame.generateParticipantResultByParticipantName(Constants.DEALER_NAME);
        ResultView.printParticipantResult(dealerResultDto);
        for (String playerName : playerNames) {
            ParticipantResultDto playerResultDto = blackJackGame.generateParticipantResultByParticipantName(playerName);
            ResultView.printParticipantResult(playerResultDto);
        }
    }

    private void additionalCardDrawStep(BlackJackGame blackJackGame) {
        if (!isDealerBlackjack(blackJackGame)) {
            additionalCardDraw(blackJackGame);
        }
    }

    private boolean isDealerBlackjack(BlackJackGame blackJackGame) {
        return blackJackGame.isDealerBlackjack();
    }

    private void additionalCardDraw(BlackJackGame blackJackGame) {
        askPlayerAdditionalCardDrawStep(blackJackGame);
        dealerAdditionalCardDrawStep(blackJackGame);
    }

    private BettingMoney generateBettingMoney(String playerName) {
        try {
            OutputView.printInputBettingMoney(playerName);
            String bettingMoney = InputView.inputBettingMoney();
            return new BettingMoney(bettingMoney);
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
            ParticipantResultDto playerResultDto = blackJackGame.generateParticipantResultByParticipantName(playerName);
            ResultView.printParticipantResult(playerResultDto);
        }
    }

    private void dealerAdditionalCardDrawStep(BlackJackGame blackJackGame) {
        while (blackJackGame.canDealerDrawCard()) {
            OutputView.printDealerReceivedMessage();
            blackJackGame.distributeDealerCard();
        }
    }

    private void printResultStep(BlackJackGame blackJackGame, BettingManager bettingManager) {
        printFinalCardResult(blackJackGame);
        calculateFinalProfit(blackJackGame, bettingManager);
        printFinalProfit(bettingManager);
    }

    private void printFinalCardResult(BlackJackGame blackJackGame) {
        ParticipantFinalResultDto dealerFinalResultDto = blackJackGame.generateParticipantFinalResultByParticipantName(Constants.DEALER_NAME);
        ResultView.printParticipantFinalResult(dealerFinalResultDto);
        List<String> playerNames = blackJackGame.getPlayerNames();
        for (String playerName : playerNames) {
            ParticipantFinalResultDto playerFinalResultDto = blackJackGame.generateParticipantFinalResultByParticipantName(playerName);
            ResultView.printParticipantFinalResult(playerFinalResultDto);
        }
    }

    private void calculateFinalProfit(BlackJackGame blackJackGame, BettingManager bettingManager) {
        bettingManager.calculateParticipantFinalProfit(blackJackGame.getRawPlayers(), blackJackGame.getDealer());
    }

    private void printFinalProfit(BettingManager bettingManager) {
        ResultView.printFinalProfit(bettingManager.generateFinalProfitDto());
    }
}
