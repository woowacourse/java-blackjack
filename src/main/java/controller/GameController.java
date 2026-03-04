package controller;

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

        System.out.println("---");

        for (String playerName : playerNames) {
            inputView.readHitOption(playerName);
        }

    }
}
