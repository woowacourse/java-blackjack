package blakjack;

import blakjack.domain.Chip;
import blakjack.domain.PlayerName;
import blakjack.view.InputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        List<PlayerName> playerNames = InputView.inputPlayerNames();

        List<Chip> chips = InputView.inputBettingMoney(playerNames);
    }
}
