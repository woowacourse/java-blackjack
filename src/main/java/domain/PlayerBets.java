package domain;

import java.util.ArrayList;
import java.util.List;

public class PlayerBets {

    private final List<PlayerBet> playersBets = new ArrayList<>();

    public void add(PlayerBet playerBet) {
        playersBets.add(playerBet);
    }

    public List<PlayerBet> getPlayersBets() {
        return playersBets;
    }
}
