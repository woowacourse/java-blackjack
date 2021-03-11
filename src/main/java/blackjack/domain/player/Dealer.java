package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.ResultType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Player {

    private static final int DRAW_STANDARD = 16;

    public Dealer(Cards cards) {
        super("딜러", cards);
    }

    @Override
    public void addCard(Card card) {
        if (calculateScore() > DRAW_STANDARD) {
            throw new IllegalArgumentException("[ERROR] 딜러의 점수가 16을 초과하여 카드를 추가할 수 없습니다.");
        }
        cards.addCard(card);
    }

    public GameResult judgeGameResultWithGamers(List<Gamer> gamers) {
        Map<Player, List<ResultType>> result = new LinkedHashMap<>();
        result.put(this, new ArrayList<>());
        for (Gamer gamer : gamers) {
            result.put(gamer, new ArrayList<>());
            Map<Player, ResultType> resultPerGamer = ResultType.judgeGameResult(this, gamer);
            result.get(gamer).add(resultPerGamer.get(gamer));
            result.get(this).add(resultPerGamer.get(this));
        }
        return GameResult.of(result);
    }

    @Override
    public boolean canDraw() {
        return calculateScore() <= DRAW_STANDARD;
    }

    @Override
    public boolean isDealer() {
        return true;
    }
}
