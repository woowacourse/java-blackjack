package blackjack;

import blackjack.domain.gambler.Name;
import blackjack.view.InputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        List<Name> playerNames = InputView.inputPlayerName();

    }
}
