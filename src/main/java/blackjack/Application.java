package blackjack;

import blackjack.view.InputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        final List<String> names = InputView.inputPlayerNames();
    }
}
