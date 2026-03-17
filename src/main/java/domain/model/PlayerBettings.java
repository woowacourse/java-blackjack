package domain.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerBettings {

    private final List<PlayerBetting> playerBettings = new ArrayList<>();

    public void save(PlayerBetting playerBetting) {
        playerBettings.add(playerBetting);
    }

    public double calculateAllPlayerBettings() {
        return playerBettings.stream()
                .mapToDouble(PlayerBetting::calculateProfit)
                .sum();
    }
}
