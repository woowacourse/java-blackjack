package controller.input;

import java.util.ArrayList;
import java.util.List;
import model.paticipant.BettingPlayer;
import model.paticipant.Players;
import view.InputView;

public class BettingPlayerReader implements PlayerReader<BettingPlayer> {

    @Override
    public Players<BettingPlayer> readPlayers() {
        List<String> names = InputView.readPlayerNames();
        List<BettingPlayer> players = new ArrayList<>();
        for (String name : names) {
            int betAmount = InputView.readBetAmount(name);
            players.add(new BettingPlayer(name, betAmount));
        }
        return new Players<>(players);
    }
}
