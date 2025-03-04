package blackjack.controller;

import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.util.StringParser;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final ResultView resultView;

    public BlackjackController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        final Players players = makePlayers();


    }

    private Players makePlayers() {
        String names = inputView.readNames();
        List<String> parsedNames = StringParser.parseComma(names);
        List<Player> players = parsedNames.stream()
                .map(Player::new)
                .toList();
        return new Players(players);
    }
}
