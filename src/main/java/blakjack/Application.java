package blakjack;

import blakjack.domain.PlayerName;
import blakjack.view.InputView;

import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        List<String> names = InputView.inputPlayerNames();
        List<PlayerName> playerNames = names.stream()
                .map(PlayerName::new)
                .collect(Collectors.toList());
    }
}
