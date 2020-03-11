package controller;

import domain.gamer.Player;
import domain.gamer.Players;
import utils.InputUtils;
import view.InputView;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class GameController {
    public void run() {
        Players players = GeneratePlayers();
    }

    private Players GeneratePlayers() {
        return InputUtils.splitAsDelimiter(InputView.inputAsPlayerName())
                .stream()
                .map(Player::new)
                .collect(collectingAndThen(toList(), Players::new));
    }
}
