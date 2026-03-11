package controller;

import java.util.ArrayList;
import model.BettingMoney;
import model.PlayerName;
import java.util.List;
import model.Agreement;
import model.Player;
import model.Players;
import view.InputView;

public class InputController {

    private static final String NAME_SPLIT_REGEX = ",";

    public Players getPlayersName() {
        List<String> stringPlayers = List.of(InputView.getNameRequest().split(NAME_SPLIT_REGEX));
        List<Player> players = new ArrayList<>();

        for (String player : stringPlayers) {
            String bettingMoneyInput = InputView.getBettingRequest(player);
            players.add(new Player(new PlayerName(player), new BettingMoney(bettingMoneyInput)));
        }

        return new Players(players);
    }

    public boolean getCondition(PlayerName name) {
        String input = InputView.getDrawRequest(name);
        return new Agreement(input).get();
    }

}
