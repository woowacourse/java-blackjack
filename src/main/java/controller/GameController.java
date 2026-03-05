package controller;

import domain.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import view.InputView;

public class GameController {
    private final InputView inputView;

    public GameController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        String rawPlayerNames = inputView.readPlayerName();
        List<String> playerNames = Arrays.stream(rawPlayerNames.split(",")).toList();

        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }

        System.out.println("---");

        for (String playerName : playerNames) {
            inputView.readHitOption(playerName);
        }

    }
}
