package domain.score;

import domain.player.Bet;
import domain.player.Name;

import java.util.*;

public class ScoreBoard {

    private final Map<Name, Bet> playersBets;
    private final Map<Name, Revenue> playersRevenues;

    public ScoreBoard(Map<Name, Bet> playersBets) {
        this.playersBets = playersBets;
        this.playersRevenues = new HashMap<>();
    }

    public void updatePlayerScore(Name name, Status status) {
        Bet bet = playersBets.get(name);
        RevenueCalculator revenueCalculator = status.getRevenueCalculator();
        Revenue revenue = revenueCalculator.calculate(bet);
        playersRevenues.put(name, revenue);
    }

    public Revenue calculateDealerRevenue() {
        List<Revenue> revenues = new ArrayList<>(playersRevenues.values());
        int sum = revenues.stream()
                .mapToInt(Revenue::getAmount)
                .sum();
        return new Revenue(sum * -1);
    }

    public Map<Name, Revenue> getPlayersRevenues() {
        return Collections.unmodifiableMap(playersRevenues);
    }
}
