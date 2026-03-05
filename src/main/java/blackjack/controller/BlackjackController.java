package blackjack.controller;

import blackjack.domain.Player;
import blackjack.utils.Parser;
import blackjack.utils.RetryExecutor;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    public void run() {
        List<Player> players = RetryExecutor.retry(this::readNicknames);
        OutputView.printStartMessage(players);
    }

    private List<Player> readNicknames() {
        String rawNicknames = InputView.readNicknames();
        List<String> nicknames = Parser.parseNickname(rawNicknames);
        return nicknames.stream()
            .map(Player::new)
            .toList();
    }


}
