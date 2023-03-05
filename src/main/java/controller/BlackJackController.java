package controller;

import domain.BlackJackGame;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.ResultCalculator;
import java.util.List;
import view.InputView;
import view.OutputView;
import view.ResultView;

public class BlackJackController {
    private final Players players;
    private final Dealer dealer;

    public BlackJackController() {
        this.players = generatePlayers();
        this.dealer = new Dealer();
        initSetting();
    }

    public void run() {
        askEachPlayers();
        dealerDistributeOrNot();
        printFinalGameStatus();
        printFinalFightResult();
    }

    private Players generatePlayers() {
        try {
            return new Players(initPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return generatePlayers();
        }
    }

    private List<String> initPlayerNames() {
        OutputView.printInputPlayerNameMessage();
        return InputView.inputPlayerNames();
    }

    private void initSetting() {
        BlackJackGame.initSettingCards(players, dealer);
        ResultView.printInitMessage(players.getPlayerNames());

        printInitMemberCards();
    }

    private void printInitMemberCards() {
        ResultView.printParticipantResult(dealer.getName(), dealer.getCardNames());
        for (Player player : players.getPlayers()) {
            ResultView.printParticipantResult(player.getName(), player.getCardNames());
        }
    }


    private void askEachPlayers() {
        System.out.println();
        for (Player player : players.getPlayers()) {
            askPlayerDistribute(player);
        }
    }

    private void askPlayerDistribute(Player player) {
        try {
            checkAdditionalDistribute(player);
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            askPlayerDistribute(player);
        }
    }

    private void checkAdditionalDistribute(Player player) {
        do {
            OutputView.printInputReceiveYesOrNotMessage(player.getName());
            ResultView.printParticipantResult(player.getName(), player.getCardNames());
        } while (player.isAbleToDraw() && isReceivable(player));
    }

    private boolean isReceivable(Player player) {
        if (InputView.inputReceiveOrNot()) {
            BlackJackGame.distributeCard(player, 1);
            return true;
        }
        return false;
    }

    private void dealerDistributeOrNot() {
        while (dealer.isAbleToDraw()) {
            BlackJackGame.distributeCard(dealer, 1);
            OutputView.printDealerReceivedMessage();
        }
    }

    private void printFinalGameStatus() {
        System.out.println();
        ResultView.printParticipantFinalResult(dealer.getName(), dealer.getCardNames(), dealer.getMaxSum());
        for (Player player : players.getPlayers()) {
            ResultView.printParticipantFinalResult(player.getName(), player.getCardNames(), player.getMaxSum());
        }
    }

    private void printFinalFightResult() {
        ResultCalculator resultCalculator = new ResultCalculator(players, dealer);
        resultCalculator.executeGame(players, dealer);
        ResultView.printFinalFightResult(resultCalculator.getFinalFightResults());
    }
}
