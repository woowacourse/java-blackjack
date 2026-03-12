package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Judge {
    private final Map<Player, WinningStatus> playerResults;

    public Judge(Dealer dealer, List<Player> players){
        playerResults = new HashMap<>();
        for(Player player: players){
            playerResults.put(player, calculateWinningStatus(dealer, player));
        }
    }

    private WinningStatus calculateWinningStatus(Dealer dealer, Player player){
        if (player.isBurst()) {
            return WinningStatus.LOSE;
        }
        if (dealer.isBurst()) {
            return WinningStatus.WIN;
        }
        if (player.getScore() > dealer.getScore()) {
            return WinningStatus.WIN;
        }
        if (player.getScore() == dealer.getScore()) {
            return WinningStatus.DRAW;
        }
        return WinningStatus.LOSE;
    }

    public int judgeDealerWinCount(){
        return (int)playerResults.values().stream()
                .filter(status -> status == WinningStatus.LOSE)
                .count();
    }

    public int judgeDealerLoseCount(){
        return (int)playerResults.values().stream()
                .filter(status -> status == WinningStatus.WIN)
                .count();
    }

    public WinningStatus getPlayerResult(Player player){
        return playerResults.get(player);
    }

    public Map<Player, WinningStatus> getPlayerResults() {
        return new HashMap<>(playerResults);
    }
}
