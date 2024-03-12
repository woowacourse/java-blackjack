package controller;

import domain.Answer;
import domain.participant.Players;
import java.util.List;
import view.InputView;
import view.OutputView;

public class InputController {

    private final InputView inputView;
    private final OutputView outputView;

    public InputController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public Players getPlayers() {
        Players players;
        do {
            players = readPlayers();
        } while (players == null);
        return players;
    }

    public Answer getAnswer(String name) {
        Answer answer;
        do {
            answer = readAnswer(name);
        } while (answer == null);
        return answer;
    }

    private Players readPlayers() {
        try {
            List<String> rawNames = inputView.readNames();
            return Players.from(rawNames);
        } catch (IllegalArgumentException exception) {
            outputView.printException(exception);
            return null;
        }
    }

    private Answer readAnswer(String name) {
        try {
            String value = inputView.readAnswer(name);
            return Answer.from(value);
        } catch (IllegalArgumentException exception) {
            outputView.printException(exception);
            return null;
        }
    }
}
