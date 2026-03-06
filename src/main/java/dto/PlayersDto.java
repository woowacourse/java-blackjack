package dto;

import domain.Dealer;
import domain.Player;
import java.util.List;

public record PlayersDto(List<Player> players, Dealer dealer) {

    @Override
    public List<Player> players() {
        return List.copyOf(players);
    }
}
