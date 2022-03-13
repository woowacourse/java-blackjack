package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.Blackjack;
import blackjack.domain.Command;
import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController();
        blackjackController.run();
    }
}
