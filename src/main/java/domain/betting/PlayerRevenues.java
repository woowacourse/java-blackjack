package domain.betting;

import domain.participant.PlayerName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerRevenues {
    private final List<PlayerRevenue> playerRevenues;

    public PlayerRevenues(List<PlayerRevenue> playerRevenues) {
        this.playerRevenues = new ArrayList<>(playerRevenues);
    }

    public PlayerRevenue calculateDealerRevenue() {
        Revenue totalPlayersRevenue = calculateTotalPlayerRevenue();
        return new PlayerRevenue(new PlayerName("dealer"), totalPlayersRevenue.reverse());
    }

    private Revenue calculateTotalPlayerRevenue() {
        int totalRevenueValue = playerRevenues.stream()
                .mapToInt(PlayerRevenue::revenueValue)
                .sum();
        return new Revenue(totalRevenueValue);
    }

    public List<PlayerRevenue> getPlayerRevenues() {
        return Collections.unmodifiableList(playerRevenues);
    }

    public Revenue getRevenueByPlayerName(PlayerName playerName) {
        return playerRevenues.stream()
                .filter(playerRevenue -> playerRevenue.isSamePlayerName(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 이름의 수익 정보가 없습니다."))
                .revenue();
    }
}
