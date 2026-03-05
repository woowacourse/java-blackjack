package dto;

import domain.Player;
import java.util.List;

public record PlayersDto(List<Player> players) {

    @Override
    public List<Player> players() {
        return List.copyOf(players);
    }
}
