package controller;

import java.util.ArrayList;
import java.util.List;
import model.Agreement;
import model.BettingMoney;
import model.Player;
import model.PlayerName;
import model.PlayerNames;
import model.Players;
import view.InputView;

public class InputController {

    private static final String NAME_SPLIT_REGEX = ",";

    public Players getPlayersName() {
        PlayerNames playerNames = getValidatedPlayerNames();
        List<Player> players = new ArrayList<>();

        for (PlayerName playerName : playerNames.getPlayerNames()) {
            String bettingMoneyInput = InputView.getBettingRequest(playerName.value());
            players.add(new Player(playerName, new BettingMoney(bettingMoneyInput)));
        }

        return new Players(players);
    }

    private PlayerNames getValidatedPlayerNames() {
        List<String> stringPlayers = List.of(InputView.getNameRequest().split(NAME_SPLIT_REGEX, -1));
        List<PlayerName> playerNames = new ArrayList<>();

        for (String player : stringPlayers) {
            playerNames.add(new PlayerName(player));
        }
        return new PlayerNames(playerNames);
    }

    public boolean getCondition(PlayerName name) {
        String input = InputView.getDrawRequest(name);
        return new Agreement(input).get();
    }

}
