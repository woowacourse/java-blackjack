package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    public static final int DRAW_BOUNDARY = 16;

    private Dealer(Cards cards) {
        super(cards);
    }

    public static Dealer init() {
        return new Dealer(Cards.empty());
    }

    public static Dealer of(Cards cards) {
        return new Dealer(cards);
    }

    public boolean hasToDraw() {
        return this.getCardScore() <= DRAW_BOUNDARY;
    }

    public Map<Participant, GameResult> getGameResult(List<Player> players) {
        Map<Participant, GameResult> gameResult = new HashMap<>();
        for (Player player : players) {
            GameResult judge = GameResult.judge(player, this);
            gameResult.put(player, judge);
        }

        return gameResult;
    }
}
