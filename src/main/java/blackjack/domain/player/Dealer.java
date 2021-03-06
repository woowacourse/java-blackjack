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
    private static final String DEALER_SCORE_RANGE_ERROR = "[ERROR] 딜러의 점수가 16을 초과하여 카드를 추가할 수 없습니다.";
    private static final String DEALER_NAME = "딜러";
    private static final int DRAW_MAXIMUM_SCORE = 16;

    public Dealer(Cards cards) {
        super(new Name(DEALER_NAME), cards);
    }

    @Override
    public void addCard(Card card) {
        validateDealerCardScore();
        cards.addCard(card);
    }

    private void validateDealerCardScore() {
        if (calculateScore() > DRAW_MAXIMUM_SCORE) {
            throw new IllegalArgumentException(DEALER_SCORE_RANGE_ERROR);
        }
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
        return calculateScore() <= DRAW_MAXIMUM_SCORE;
    }

    @Override
    public boolean isBust() {
        return cards.isBust();
    }
}
