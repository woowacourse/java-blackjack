package blackjack.controller;

import blackjack.view.InputView;
import java.util.List;

public class BlackjackController {

    public void run() {
        List<String> names = InputView.requestNames();
    }
}
