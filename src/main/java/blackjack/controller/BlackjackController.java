package blackjack.controller;

import blackjack.domain.User;
import blackjack.util.InputParser;
import blackjack.view.InputView;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;

    public BlackjackController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        List<User> users = readUsers();
    }

    private List<User> readUsers() {
        String userName = inputView.readUserName();
        return InputParser.createUser(userName);
    }

}
