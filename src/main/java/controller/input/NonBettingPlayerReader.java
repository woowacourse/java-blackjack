package controller.input;

import java.util.List;
import java.util.stream.Collectors;
import model.paticipant.NonBettingPlayer;
import model.paticipant.Player;
import model.paticipant.Players;
import view.InputView;

public class NonBettingPlayerReader implements PlayerReader {

    @Override
    public Players readPlayers() {
        List<String> names = InputView.readPlayerNames();
        List<Player> players = names.stream()
                .map(NonBettingPlayer::new)
                .collect(Collectors.toList());
        return new Players(players);
    }
}
