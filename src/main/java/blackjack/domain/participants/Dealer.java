package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.result.JudgeResult;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// TODO 딜러 싱글톤 여부 검토
public class Dealer extends Participant {

    private static final int CARD_TAKE_LIMIT = 17;
    private static final int INITIAL_DEALER_CARD_OPEN_INDEX = 0;

    public Dealer(String name) {
        super(name);
    }

    public Map<String, JudgeResult> judgeAllPlayersResult(Players players) {
        Map<String, JudgeResult> playerJudgeResults = new LinkedHashMap<>();
        for (Player player : players.players()) {
            playerJudgeResults.put(player.getName(), judge(player));
        }
        return playerJudgeResults;
    }

    public JudgeResult judge(Player player) {
        if (player.isBust()) {
            return JudgeResult.LOSE;
        }
        if (isBust()) {
            return JudgeResult.WIN;
        }
        return JudgeResult.match(player.computeCardsScore(), computeCardsScore());
    }

    @Override
    public boolean isAvailable() {
        return computeCardsScore() < CARD_TAKE_LIMIT;
    }

    public Card openFirstCard() {
        List<Card> cards = getCards();
        if (cards.isEmpty()) {
            throw new IllegalStateException("딜러가 아직 카드를 가지고 있지 않습니다.");
        }
        return getCards().get(INITIAL_DEALER_CARD_OPEN_INDEX);
    }
}
