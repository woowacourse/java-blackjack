package blackjack.domain.betting;

import blackjack.domain.game.PlayerResult;
import blackjack.domain.game.result.GameResult;
import blackjack.domain.participant.Name;
import java.util.HashMap;
import java.util.Map;

//TODO 가변 클래스인게 마음에 안듬
public class Betting {

    private final Map<Name, Money> bettingMoney = new HashMap<>();

    public void bet(Name name, Money money) {
        bettingMoney.put(name, money);
    }

    public Money getPrize(PlayerResult gameResult) {
        GameResult result = gameResult.getResult();
        Name playerName = gameResult.getName();
        Money bet = bettingMoney.get(playerName);
        return result.calculatePrize(bet);
    }
}
