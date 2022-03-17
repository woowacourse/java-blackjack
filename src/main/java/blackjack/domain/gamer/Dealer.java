package blackjack.domain.gamer;

import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.card.Card;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.Result;

public class Dealer extends Gamer {

    private static final int DRAWABLE_NUMBER = 16;

    public Dealer() {
        super("딜러");
    }

    @Override
    public boolean canDraw() {
        return calculateScore() <= DRAWABLE_NUMBER;
    }

    public GameResult judgeResult(final Players players) {
        Map<Player, Result> result = new LinkedHashMap<>();
        judgePlayersResult(result, players);
        return new GameResult(result);
    }

    private void judgePlayersResult(final Map<Player, Result> gameResult, Players players) {
        players.getPlayers()
            .forEach(player -> gameResult.put(player, Result.judge(this, player)));
    }

    public Card getFirstCard() {
        return getCards().get(0);
    }
}
