package controller;

import model.Dealer;
import model.GameManager;
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
        OutputView.printHands(dealer, players);
    }
}
