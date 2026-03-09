package controller;

import dto.PlayerName;
import java.util.List;
import model.Agreement;
import model.Player;
import model.Players;
import view.InputView;

public class InputController {

    public Players getPlayersName() {
        List<String> stringPlayers = InputView.getNameRequest();
        List<Player> players = stringPlayers.stream()
                .map(player -> new Player(new PlayerName(player)))
                .toList();

        return new Players(players);
    }

    public boolean getCondition(PlayerName name) {
        String input = InputView.getDrawRequest(name.toString());
        return new Agreement(input).get();
    }

}
