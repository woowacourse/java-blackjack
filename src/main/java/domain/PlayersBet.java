package domain;

import java.util.ArrayList;
import java.util.List;

public class PlayersBet {

    private final List<PlayerBet> playerBets = new ArrayList<>();

    public void add(PlayerBet playerBet) {
        playerBets.add(playerBet);
    }
}
