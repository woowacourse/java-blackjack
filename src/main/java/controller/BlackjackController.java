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
    }

    private void receiveAdditionalCard(Player player,GameManager gameManager) {
        while (Intent.from(InputView.readIntent(player.getNickname())).equals(Intent.Y)) {
            gameManager.divideCardByParticipant(player,1);
            OutputView.printDivision(player);
        };
    }
}
