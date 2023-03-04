package controller;

import java.util.List;
import java.util.stream.Collectors;

import domain.BlackJackGame;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.ResultCalculator;
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

    private void initSetting() {
        BlackJackGame.initSettingCards(players, dealer);
        ResultView.printParticipantResult(dealer.getName(), dealer.getCardNames());
        for (Player player : players.getPlayers()) {
            ResultView.printParticipantResult(player.getName(), player.getCardNames());
        }
    }

    private Players generatePlayers() {
        try {
            List<String> playerNames = initPlayerNames();
            Players players = new Players(playerNames.stream()
                    .map(Name::new)
                    .map(Player::new)
                    .collect(Collectors.toUnmodifiableList()));
            ResultView.printInitMessage(playerNames);
            return players;
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return generatePlayers();
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
        } while (player.checkCardsCondition() && isReceivable(player));
    }

    private boolean isReceivable(Player player) {
        String receiveOrNot = InputView.inputReceiveOrNot();
        if (receiveOrNot.equals("y")) {
            BlackJackGame.distributeCard(player, 1);
            return true;
        }
        return false;
    }

    private void dealerDistributeOrNot() {
        while (dealer.checkCardsCondition()) {
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
