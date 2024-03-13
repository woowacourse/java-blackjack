package blackjack.model.betting;

import blackjack.model.player.Player;

import java.util.HashMap;
import java.util.Map;

public class BettingBoard {
    private final Map<Player, BettingMoney> bettings; // Todo: Player를 저장할 것인가 Player의 name을 저장할 것인가

    public BettingBoard(final Map<Player, BettingMoney> bettings) {
        this.bettings = new HashMap<>(bettings);
    }
}
