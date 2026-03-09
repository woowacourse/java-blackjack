package model;

import java.util.ArrayList;
import java.util.List;
import model.dto.PlayerWinning;

public class PlayersWinning {

    private final List<PlayerWinning> playersWinnings = new ArrayList<>();

    public void add(PlayerWinning playerWinning) {
        playersWinnings.add(playerWinning);
    }

    public List<PlayerWinning> getFormattedPlayersWinnings() {
        return List.copyOf(playersWinnings);
    }
}
