package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.result.JudgeResult;
import blackjack.dto.HandStatus;
import java.util.List;

public class Dealer extends Participant {

    private static final int CARD_TAKE_LIMIT = 17;
    private static final int INITIAL_DEALER_CARD_OPEN_INDEX = 0;

    public Dealer(final String name) {
        super(name);
    }

    public Dealer(final String name, final List<Card> cards) {
        super(name, cards);
    }

    public int computeProfitOf(final Player player) {
        final JudgeResult playerResult = judge(player);
        return playerResult.profit(player.getBettingMoney());

    }

    private JudgeResult judge(final Player player) {
        if (player.isBust()) {
            return JudgeResult.LOSE;
        }
        if (isBust()) {
            return JudgeResult.WIN;
        }
        if (!isBlackJack() && player.isBlackJack()) {
            return JudgeResult.BLACKJACK_WIN;
        }
        return JudgeResult.matchWithoutBlackJackConsider(player.computeCardsScore(), computeCardsScore());
    }


    @Override
    public boolean isHitAble() {
        return computeCardsScore() < CARD_TAKE_LIMIT;
    }

    @Override
    public HandStatus toHandStatus() {
        return new HandStatus(getName(), List.of(cards().get(INITIAL_DEALER_CARD_OPEN_INDEX)));
    }
}
