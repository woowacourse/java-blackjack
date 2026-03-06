package controller;

import domain.GameTable;
import domain.Hand;
import domain.Participant;
import domain.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import strategy.RandomStrategy;
import view.InputView;

public class Controller {

    private GameTable gameTable;

    public void run() {
        setupPhase();
        gamePhase();
    }

    private void setupPhase() {
        gameTable = new GameTable();
        dealerSetup();
        playerSetup();
    }

    private void dealerSetup() {
    }

    private void playerSetup() {
        String playerNames = InputView.readPlayers();
        List<String> names = Arrays.stream(playerNames.split(",")).toList();

        for (String name : names) {
            Hand hand = new Hand(new RandomStrategy(), new ArrayList<>());
            Player player = new Player(name, hand);
            player.draw();
            player.draw();
            gameTable.addPlayer(new Player(name, hand));
        }
    }

    private void gamePhase() {
    }
}
