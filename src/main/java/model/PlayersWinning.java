package model;

import java.util.ArrayList;
import java.util.List;
import dto.PlayerWinning;

public class PlayersWinning {

    private final List<PlayerWinning> playersWinnings = new ArrayList<>();

    public void add(PlayerWinning playerWinning) {
        playersWinnings.add(playerWinning);
    }

    public List<PlayerWinning> getPlayersWinnings() {
        return List.copyOf(playersWinnings);
    }
}
