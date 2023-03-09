package controller;

import java.util.List;
import java.util.Map;

import domain.AdditionalDrawStatus;
import domain.BlackJackGame;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.ResultCalculator;
import util.Constants;
import view.InputView;
import view.OutputView;
import view.ResultView;

public class BlackJackController {

    public void run() {
        BlackJackGame blackJackGame = generateBlackJackGame();
        play(blackJackGame);
        printFinalCardResult(blackJackGame);

        ResultCalculator resultCalculator = new ResultCalculator(blackJackGame.getDealer(), blackJackGame.getPlayers());
        calculateResult(blackJackGame, resultCalculator);
        printFightResult(resultCalculator);
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

    private void play(BlackJackGame blackJackGame) {
        initSettingStep(blackJackGame);
        askPlayerAdditionalCardDrawStep(blackJackGame);
        dealerAdditionalCardDrawStep(blackJackGame);
    }

    private void initSettingStep(BlackJackGame blackJackGame) {
        List<String> playerNames = blackJackGame.getPlayerNames();
        ResultView.printInitMessage(playerNames);
        ResultView.printParticipantResult(Constants.DEALER_NAME, blackJackGame.findCardNamesByParticipantName(Constants.DEALER_NAME));
        for (String playerName : playerNames) {
            List<String> findCardNames = blackJackGame.findCardNamesByParticipantName(playerName);
            ResultView.printParticipantResult(playerName, findCardNames);
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

    private void calculateResult(BlackJackGame blackJackGame, ResultCalculator resultCalculator) {
        for (Player player : blackJackGame.getRawPlayers()) {
            resultCalculator.calculate(player, blackJackGame.getDealer());
        }
    }

    private void printFightResult(ResultCalculator resultCalculator) {
        Map<String, String> resultByPlayerName = resultCalculator.getFinalFightResults();
        ResultView.printFinalFightResult(resultByPlayerName);
    }
}
