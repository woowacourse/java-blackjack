package blackjack.controller;

import blackjack.domain.Player;
import blackjack.domain.Name;
import blackjack.domain.Players;
import blackjack.view.InputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {

    public void run() {
        List<String> names = InputView.requestNames();
        Players players = createPlayers(names);

    }

    private Players createPlayers(List<String> names) {
        return new Players(names.stream()
                .map(Name::new)
                .map(Player::from)
                .collect(Collectors.toList()));
    }
}
