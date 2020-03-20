package domain.user;

import common.PlayerDto;
import common.PlayersDto;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    private static Players of(List<Player> players) {
        return new Players(players);
    }

    public static Players join(PlayersDto playersDto) {
        List<Player> players = new ArrayList<>();
        for (PlayerDto playerDto : playersDto.getPlayerDtos()) {
            Player player = Player.join(playerDto);
            players.add(player);
        }
        return of(players);
    }
}
