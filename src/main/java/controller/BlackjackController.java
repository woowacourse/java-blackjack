package controller;

import model.participant.Dealer;
import model.GameManager;
import model.participant.Player;
import model.participant.Players;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackjackController {

    public void run() {
        List<String> values = InputView.readPlayerNames();
        Players players = Players.from(values);
        Dealer dealer = Dealer.newInstance();
        GameManager gameManager = new GameManager(dealer, players);
        gameManager.shuffle();
        gameManager.divideAllParticipant();
        OutputView.printDivisionStart(dealer, players);

        for (Player player : players.getPlayers()) {
            receiveAdditionalCard(player, gameManager);
        }
        receiveAdditionalCard(dealer, gameManager);

        OutputView.printAllParticipantScore(dealer, players);

        gameManager.calculateVictory();
        OutputView.printResult(dealer, players);
    }

    private void receiveAdditionalCard(Player player, GameManager gameManager) {
        while (satisfiedConditionByPlayer(player)) {
            gameManager.divideCardByParticipant(player, 1);
            OutputView.printCurrentHands(player);
        }
    }

    private void receiveAdditionalCard(Dealer dealer, GameManager gameManager) {
        while (satisfiedConditionByDealer(dealer)) {
            gameManager.divideCardByParticipant(dealer, 1);
            OutputView.printStandingDealer(dealer);
        }
    }

    private boolean satisfiedConditionByPlayer(Player player) {
        return player.canHit() && agreeIntent(player);
    }

    private boolean satisfiedConditionByDealer(Dealer dealer) {
        return dealer.isNotUp();
    }

    private boolean agreeIntent(Player player) {
        return Intent.from(InputView.readIntent(player.getNickname())).equals(Intent.Y);
    }
}
