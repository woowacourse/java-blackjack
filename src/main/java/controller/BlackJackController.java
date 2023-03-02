package controller;

import java.util.List;
import java.util.stream.Collectors;

import domain.BlackJackGame;
import domain.result.ResultCalculator;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import view.InputView;
import view.OutputVIew;
import view.ResultView;

public class BlackJackController {
    private Players players;
    private Dealer dealer;

    public BlackJackController() {
        OutputVIew.printInputPlayerNameMessage();
        List<String> playerNames = InputView.inputPlayerNames();
        ResultView.printInitMessage(playerNames);
        this.players = generatePlayers(playerNames);
        this.dealer = new Dealer();
        initSetting();
    }

    private void initSetting() {
        BlackJackGame.initSettingCards(players, dealer);
        ResultView.printParticipantResult(dealer.getName(), dealer.getCardNames());
        for (Player player : players.getPlayers()) {
            ResultView.printParticipantResult(player.getName(), player.getCardNames());
        }
    }

    private static Players generatePlayers(List<String> playerNames) {
        Players players = new Players(playerNames.stream()
                .map(Name::new)
                .map(Player::new)
                .collect(Collectors.toUnmodifiableList()));
        return players;
    }

    public void askEachPlayers() {
        System.out.println();
        for (Player player : players.getPlayers()) {
            askPlayerDistribute(player);
        }
    }

    private void askPlayerDistribute(Player player) {
        do {
            OutputVIew.printInputReceiveYesOrNotMessage(player.getName());
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

    public void dealerDistributeOrNot() {
        while (dealer.checkCardsCondition()) {
            BlackJackGame.distributeCard(dealer, 1);
            OutputVIew.printDealerReceivedMessage();
        }
    }

    public void printFinalGameStatus() {
        System.out.println();
        ResultView.printParticipantFinalResult(dealer.getName(), dealer.getCardNames(), dealer.getMaxSum());
        for (Player player : players.getPlayers()) {
            ResultView.printParticipantFinalResult(player.getName(), player.getCardNames(), player.getMaxSum());
        }
    }

    public void printFinalFightResult() {
        ResultCalculator resultCalculator = new ResultCalculator(players, dealer);
        resultCalculator.executeGame(players, dealer);
        ResultView.printFinalFightResult(resultCalculator.getFinalFightResults());
    }
}
