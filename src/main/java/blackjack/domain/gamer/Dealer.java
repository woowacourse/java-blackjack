package blackjack.domain.gamer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.Result;
import blackjack.domain.result.Results;

public class Dealer extends Gamer {

    private static final int DRAWABLE_NUMBER = 16;
    private static final String CAN_NOT_DRAWABLE_ERROR_MESSAGE = "%s 카드의 합이 %d을 초과했기 때문에 카드를 뽑을 수 없습니다.";

    public Dealer(final Cards cards) {
        super("딜러", cards);
    }

    @Override
    public void drawCard(Card card) {
        if (!canDraw()) {
            throw new IllegalArgumentException(String.format(CAN_NOT_DRAWABLE_ERROR_MESSAGE, name, DRAWABLE_NUMBER));
        }
        cards.add(card);
    }

    @Override
    public boolean canDraw() {
        return calculateScore() <= DRAWABLE_NUMBER;
    }

    public Map<Gamer, Results> judgeResult(Players players) {
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
}
