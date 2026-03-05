package controller;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import model.Agreement;
import model.Player;
import model.PlayerName;
import model.Players;
import view.InputView;

public class InputController {
    private final InputView inputView;
    private final Scanner scanner = new Scanner(System.in);

    public InputController(InputView inputView) {
        this.inputView = inputView;
    }

    public Players getParticipantsName() {
        inputView.printNameRequest();
        String nameInput = getInput();
        List<Player> players = Arrays.stream(nameInput.split(","))
                .map((name) -> new Player(new PlayerName(name)))
                .toList();

        return new Players(players);
    }

    public boolean getCondition(String name) {
        inputView.printDrawRequest(name);
        return new Agreement(getInput()).get();
    }

    private String getInput() {
        return scanner.nextLine();
    }
}
