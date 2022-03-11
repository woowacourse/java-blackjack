package blackjack.model.dto;

import java.util.List;

public class PlayersDTO {

    private final List<PlayerDTO> players;

    public PlayersDTO(List<PlayerDTO> players) {
        this.players = players;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }
}
