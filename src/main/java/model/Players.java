package model;

import constant.ErrorMessage;
import java.util.List;
import model.dto.PlayerResult;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(List<Player> players) {
        if(players.stream().distinct().toList().size() != players.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_NAME.getMessage());
        }
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public List<PlayerResult> getPlayersResult() {
        return List.copyOf(players.stream().map(Participant::getResult).toList());
    }
}
