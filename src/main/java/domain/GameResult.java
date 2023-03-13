package domain;

import domain.participants.Dealer;
import domain.participants.Player;
import domain.participants.Players;

import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private final Map<Player, Result> gameResult;

    public GameResult() {
        this.gameResult = new HashMap<>();
    }

    public void calculatePlayersResult(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            gameResult.put(player, calculatePlayerResult(dealer,player));
        }
    }

    public Result calculatePlayerResult(Dealer dealer, Player player) {
        if (player.isBust() || (dealer.dealerWin(player.getCardsSum()) && !dealer.isBust())) {
            return Result.LOSE;
        }
        else if (dealer.getCardsSum() == player.getCardsSum()) {
            return Result.DRAW;
        }
        else if(player.isBlackJack()){
            return Result.BLACKJACKWIN;
        }
        return Result.WIN;
    }

    public int calculateDealerProfit(Players players) {
        return players.getPlayers().stream()
                .map(s-> -gameResult.get(s).calculateResult(s.getMoney()))
                .reduce(0,Integer::sum);
    }
}
