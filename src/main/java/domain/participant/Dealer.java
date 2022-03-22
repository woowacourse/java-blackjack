package domain.participant;

import java.math.BigDecimal;
import java.util.List;

public final class Dealer extends Participant {

    private static final int MAX_CARD_SUM = 16;
    private static final String NAME = "딜러";
    private static final int SHIFT_NUM = -1;

    public Dealer() {
        super(NAME);
    }

    public BigDecimal getResultMoney(List<BigDecimal> playersResult) {
        BigDecimal dealerResult = BigDecimal.ZERO;
        for (BigDecimal bigDecimal : playersResult) {
            dealerResult = dealerResult.add(bigDecimal.multiply(BigDecimal.valueOf(SHIFT_NUM)));
        }
        return dealerResult;
    }

    @Override
    public boolean canDrawCard() {
        return cards.calculateScore() <= MAX_CARD_SUM;
    }
}
