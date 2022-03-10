package blackjack;

import blackjack.domain.Name;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackApplication {

    public static void main(String[] args) {
        List<Name> playerNames = inputPlayerNames();
    }

    private static List<Name> inputPlayerNames() {
        try {
            return InputView.inputPlayerNames().stream()
                    .map(Name::new)
                    .collect(Collectors.toUnmodifiableList());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPlayerNames();
        }
    }
}
