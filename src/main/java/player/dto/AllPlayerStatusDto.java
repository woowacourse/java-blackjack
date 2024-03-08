package player.dto;

import java.util.ArrayList;
import java.util.List;
import player.Name;
import player.Player;
import player.Players;

public record AllPlayerStatusDto(List<SinglePlayerStatusDto> multiPlayersStatus) {

    public static AllPlayerStatusDto of(Players players) {
        ArrayList<SinglePlayerStatusDto> singlePlayerStatus = new ArrayList<>();

        for (Player player : players.getPlayers()) {
            singlePlayerStatus.add(SinglePlayerStatusDto.from(player));
        }
        return new AllPlayerStatusDto(singlePlayerStatus);
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
