package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private Map<User, PlayerResult> gameResult = new HashMap<>();

    public PlayerResult calculatePlayerResult(Dealer dealer, User user) {
        if (dealer.status == Status.BLACKJACK && user.status == Status.BLACKJACK) {
            return PlayerResult.DRAW;
        }
        return null;
    }
}
