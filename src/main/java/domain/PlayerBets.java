package domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PlayerBets {

    private final List<PlayerBet> playersBets = new ArrayList<>();

    public void add(PlayerBet playerBet) {
        playersBets.add(playerBet);
    }

    public List<BigDecimal> bettingAmounts() {
        return playersBets.stream()
                .map(PlayerBet::amount)
                .toList();
    }

    public void applyGameResult(Dealer dealer) {
        for (PlayerBet playersBet : playersBets) {
            playersBet.applyResult(dealer);
        }

    }

    public List<PlayerBet> getPlayersBets() {
        return playersBets;
    }
}
