package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> playerList;

    public Players(List<Player> playerList) {
        this.playerList = playerList;
    }

    public static Players from(List<PlayerDto> dtos) {
        List<Player> players = dtos.stream()
                .map(dto -> new Player(new PlayerName(dto.getName()), new BetAmount(dto.getBetAmount())))
                .collect(Collectors.toList());
        return new Players(players);
    }

    public List<Player> getGamePlayers() {
        return playerList;
    }

    public int getSize() {
        return playerList.size();
    }

}
