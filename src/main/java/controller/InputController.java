package controller;

import constant.ErrorMessage;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import model.Agreement;
import model.Player;
import model.dto.PlayerName;
import model.Players;
import view.InputView;

public class InputController {
    private static final String NAME_SPLIT_REGEX = ",";

    private final Scanner scanner = new Scanner(System.in);

    public Players getParticipantsName() {
        InputView.printNameRequest();
        String nameInput = getInput();
        List<Player> players = Arrays.stream(nameInput.split(NAME_SPLIT_REGEX))
                .map((name) -> new Player(new PlayerName(name)))
                .toList();

        return new Players(players);
    }

    public boolean getCondition(String name) {
        InputView.printDrawRequest(name);
        return new Agreement(getInput()).get();
    }

    private String getInput() {
        String input = scanner.nextLine();

        if(input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_IS_BLANK.getMessage());
        }
        return input;
    }
}
