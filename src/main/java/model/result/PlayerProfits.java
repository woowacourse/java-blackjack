package model.result;

import dto.result.ParticipantProfit;
import java.util.ArrayList;
import java.util.List;

public class PlayerProfits {
    private final List<ParticipantProfit> playerProfits = new ArrayList<>();

    public void addPlayerProfit(ParticipantProfit playerProfit) {
        playerProfits.add(playerProfit);
    }

    public List<ParticipantProfit> getPlayerProfits() {
        return List.copyOf(playerProfits);
    }
}
