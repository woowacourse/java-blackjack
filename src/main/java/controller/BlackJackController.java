package controller;

import View.InputView;
import domain.user.Players;

public class BlackJackController {
    public static void run() {
        new Players(InputView.readPlayerNames());
    }
}
