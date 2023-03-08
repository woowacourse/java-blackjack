package blackjack.domain.participants;

import blackjack.domain.result.JudgeResult;
import blackjack.dto.HandStatus;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    private static final int CARD_TAKE_LIMIT = 17;
    private static final int INITIAL_DEALER_CARD_OPEN_INDEX = 0;

    public Dealer(final String name) {
        super(name);
    }

    public Map<String, JudgeResult> judgeAllPlayersResult(final Players players) {
        final Map<String, JudgeResult> playerJudgeResults = new LinkedHashMap<>();
        for (final Player player : players.players()) {
            playerJudgeResults.put(player.getName(), judge(player));
        }
        return playerJudgeResults;
    }

    public JudgeResult judge(final Player player) {
        if (player.isBust()) {
            return JudgeResult.LOSE;
        }
        if (isBust()) {
            return JudgeResult.WIN;
        }
        return JudgeResult.match(player.computeCardsScore(), computeCardsScore());
    }

    @Override
    public boolean isAbleToHit() {
        return computeCardsScore() < CARD_TAKE_LIMIT;
    }

    @Override
    public HandStatus toHandStatus() {
        return new HandStatus(getName(), List.of(cards().get(INITIAL_DEALER_CARD_OPEN_INDEX)));
    }
}
