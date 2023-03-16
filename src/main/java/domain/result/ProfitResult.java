package domain.result;

import domain.game.GameStatus;
import domain.money.Bet;
import domain.money.Profit;
import domain.user.Playable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ProfitResult {
    
    private static final String PROFIT_IS_NOT_EXISTS = "수익이 존재하지 않습니다.";
    
    private final Map<Playable, Profit> profitMap = new HashMap<>();
    
    public void accumulate(Playable player, Bet bet, GameStatus gameStatus) {
        this.profitMap.put(player, Profit.create(bet, gameStatus));
    }
    
    public Profit getDealerProfit() {
        return this.profitMap.values().stream()
                .reduce(Profit::add)
                .map(Profit::negate)
                .orElseThrow(() -> new IllegalStateException(PROFIT_IS_NOT_EXISTS));
    }
    
    public Map<Playable, Profit> getProfitMap() {
        return Collections.unmodifiableMap(this.profitMap);
    }
    
}
