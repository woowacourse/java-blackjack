package controller;

import java.util.ArrayList;
import model.BattingMoney;
import model.PlayerName;
import java.util.List;
import model.Agreement;
import model.Player;
import model.Players;
import view.InputView;

public class InputController {

    public Players getPlayersName() {
        List<String> stringPlayers = InputView.getNameRequest();
        List<Player> players = new ArrayList<>();

        for(String player : stringPlayers) {
            String battingMoneyInput = InputView.getBattingRequest(player);
            players.add(new Player(new PlayerName(player), new BattingMoney(battingMoneyInput)));
        }

        return new Players(players);
    }

    public boolean getCondition(PlayerName name) {
        String input = InputView.getDrawRequest(name);
        return new Agreement(input).get();
    }

}
