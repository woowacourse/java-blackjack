package domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import view.ResultView;

public class Players {

    private final List<Player> players;

    public Players(List<String> playerNames) {
        this.players = playerNames.stream()
                .map(Name::new)
                .map(Player::new)
                .collect(Collectors.toUnmodifiableList());

//        this.players = new ArrayList<>();
//        for (String playerName : playerNames) {
//            this.players.add(new Player(new Name(playerName)));
//        }
        ResultView.printInitMessage(playerNames);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
