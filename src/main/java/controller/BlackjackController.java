package controller;

import model.Dealer;
import model.GameManager;
import model.Player;
import model.Players;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackjackController {

    public void run() {
        List<String> values = InputView.readPlayerNames();
        Players players = Players.from(values);
        Dealer dealer = Dealer.of();
        GameManager gameManager = new GameManager(dealer, players);
        gameManager.divideAllParticipant(2);
        OutputView.printDivisionStart(dealer, players);

        for (Player player : players.getPlayers()) {
            receiveAdditionalCard(player, gameManager);
        }
        receiveAdditionalCard(dealer, gameManager);
    }

    private void receiveAdditionalCard(Player player,GameManager gameManager) {
        while (satisfiedConditionByPlayer(player)) {
            gameManager.divideCardByParticipant(player,1);
            OutputView.printDivision(player);
        };
    }

    private void receiveAdditionalCard(Dealer dealer,GameManager gameManager) {
        while (satisfiedConditionByDealer(dealer)) {
            gameManager.divideCardByParticipant(dealer,1);
            OutputView.printDivision(dealer);
        };
    }

    private boolean satisfiedConditionByPlayer(Player player) {
        return player.isNotBust() && agreeIntent(player);
    }

    private boolean satisfiedConditionByDealer(Dealer dealer) {
        return dealer.isNotUp();
    }

    private boolean agreeIntent(Player player) {
        return Intent.from(InputView.readIntent(player.getNickname())).equals(Intent.Y);
    }
}
