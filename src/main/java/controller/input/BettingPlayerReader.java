package controller.input;

import java.util.ArrayList;
import java.util.List;
import model.paticipant.BettingPlayer;
import model.paticipant.Player;
import model.paticipant.Players;
import view.InputView;

public class BettingPlayerReader implements PlayerReader {

    @Override
    public Players readPlayers() {
        List<String> names = InputView.readPlayerNames();
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            int betAmount = InputView.readBetAmount(name);
            players.add(new BettingPlayer(name, betAmount));
        }
        return new Players(players);
    }
}
