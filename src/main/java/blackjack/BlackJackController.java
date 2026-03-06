package blackjack;

import java.util.List;

public class BlackJackController {
    InputView inputView = new InputView();

    public void run() {
        List<String> names = inputView.readNames();
        System.out.println(names);
    }
}
