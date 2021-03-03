package blackjack.controller;

import blackjack.view.InputView;

import java.util.List;
import java.util.Scanner;

public class GameController {
    private final InputView inputView = new InputView(new Scanner(System.in));

    public void start() {
        List<String> playerNames = inputView.getPlayerNames();
    }
}
