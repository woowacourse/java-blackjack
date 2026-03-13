package model.result;

import java.util.ArrayList;
import java.util.List;
import dto.result.PlayerWinning;

public class PlayersWinning {

    private final List<PlayerWinning> playersWinnings = new ArrayList<>();

    public void add(PlayerWinning playerWinning) {
        playersWinnings.add(playerWinning);
    }

    public List<PlayerWinning> getFormattedPlayersWinnings() {
        return List.copyOf(playersWinnings);
    }
}
