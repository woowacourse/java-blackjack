package blackjack;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.Name;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.view.InputView;

public class BlackjackApplication {

    public static void main(String[] args) {
        requestPlayers();

    }

    private static Players requestPlayers() {
        List<String> inputNames = InputView.requestNames();

        try {
            List<Player> players = inputNames.stream()
                .map(Name::new)
                .map(Player::new)
                .collect(Collectors.toList());
            return new Players(players);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return requestPlayers();
        }
    }
}
