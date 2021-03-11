package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.ResultType;
import blackjack.domain.state.State;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Player {

    private static final int DRAW_STANDARD = 16;

    public Dealer(State state) {
        super("딜러", state);
    }

    @Override
    public void addCard(Card card) {
        if (calculateScore() > DRAW_STANDARD) {
            throw new IllegalArgumentException("[ERROR] 딜러의 점수가 16을 초과하여 카드를 추가할 수 없습니다.");
        }
        state = state.draw(card);
    }

    public GameResult judgeGameResultWithGamers(List<Gamer> gamers) {
        Map<Player, ResultType> gamersResult = new HashMap<>();
        for (Gamer gamer : gamers) {
            gamersResult.put(gamer, ResultType.judgeGameResult(this, gamer));
        }
        return GameResult.of(gamersResult);
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
