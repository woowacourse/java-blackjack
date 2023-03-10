package domain.game;

import domain.money.Bet;
import domain.money.Profit;
import domain.user.Player;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GameProfit {
    
    private static final String PROFIT_IS_NOT_EXISTS = "수익이 존재하지 않습니다.";
    
    private final Map<Player, Profit> profitMap = new HashMap<>();
    
    public static GameProfit create(GameBet gameBet, GameResult gameResult) {
        GameProfit gameProfit = new GameProfit();
        for (Player player : gameBet.getBetMap().keySet()) {
            gameProfit.accumulate(player, gameBet.getPlayerBet(player), gameResult.getPlayerResult(player));
        }
        return gameProfit;
    }
    
    private void accumulate(Player player, Bet bet, ResultStatus status) {
        this.profitMap.put(player, Profit.create(bet, status));
    }
    
    public Profit getDealerProfit() {
        return this.profitMap.values().stream()
                .reduce(Profit::add)
                .orElseThrow(() -> new IllegalStateException(PROFIT_IS_NOT_EXISTS));
    }
    
    public Map<Player, Profit> getProfitMap() {
        return Collections.unmodifiableMap(this.profitMap);
    }
    
}
