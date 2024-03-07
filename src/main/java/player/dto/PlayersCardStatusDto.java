package player.dto;

import java.util.ArrayList;
import java.util.List;
import player.Name;
import player.Player;
import player.Players;

public record PlayersCardStatusDto(List<SinglePlayerStatusDto> multiPlayersStatus) {

    public static PlayersCardStatusDto of(Players players) {
        ArrayList<SinglePlayerStatusDto> singlePlayerStatus = new ArrayList<>();

        for (Player player : players.getPlayers()) {
            singlePlayerStatus.add(SinglePlayerStatusDto.from(player));
        }
        return new PlayersCardStatusDto(singlePlayerStatus);
    }

    public List<Name> getNames() {
        return multiPlayersStatus.stream()
                .map(SinglePlayerStatusDto::name)
                .toList();
    }

    public int size() {
        return multiPlayersStatus.size();
    }
}
