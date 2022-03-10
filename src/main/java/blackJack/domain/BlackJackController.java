package blackJack.domain;

import java.util.List;
import java.util.stream.Collectors;

import blackJack.domain.participant.Player;
import blackJack.domain.view.InputView;

public class BlackJackController {

    private List<Player> getPlayerNames() {
        try {
            List<String> playerNames = InputView.inputPlayerNames();
            return playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toUnmodifiableList());
        } catch (IllegalArgumentException e) {
            return getPlayerNames();
        }
    }
}
