package blackjack.domain.gamer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.card.Card;
import blackjack.domain.result.Result;
import blackjack.domain.result.Results;

public class Dealer extends Gamer {

    private static final int DRAWABLE_NUMBER = 16;

    public Dealer() {
        super("딜러");
    }

    @Override
    public boolean canDraw() {
        return calculateScore() <= DRAWABLE_NUMBER;
    }

    public Map<Gamer, Results> judgeResult(final Players players) {
        Map<Gamer, Results> gameResult = new LinkedHashMap<>();
        initGameResult(gameResult, players);
        judgePlayers(gameResult, players);
        return gameResult;
    }

    private void initGameResult(Map<Gamer, Results> gameResult, Players players) {
        gameResult.put(this, new Results(new ArrayList<>()));
        players.initGameResult(gameResult);
    }

    private void judgePlayers(Map<Gamer, Results> gameResult, Players players) {
        players.getPlayers().forEach(player -> {
            Map<Gamer, Result> judgeResult = Result.judge(this, player);
            gameResult.get(this).add(judgeResult.get(this));
            gameResult.get(player).add(judgeResult.get(player));
        });
    }

    public Card getFirstCard() {
        return getCards().get(0);
    }
}
