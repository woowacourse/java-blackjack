package blackjack.domain.user;

import blackjack.domain.card.CardGroup;
import blackjack.domain.result.Score;

public class Dealer extends User {

    public static final String DEALER_NAME = "딜러";
    private static final int FIRST_OPEN_CARD_COUNT = 1;
    private static final int DRAW_LIMIT_SCORE = 16;

    public Dealer(CardGroup initialGroup) {
        super(DEALER_NAME, initialGroup);
    }

    @Override
    public CardGroup getFirstOpenCardGroup() {
        return getCardGroups().getSubCardGroup(FIRST_OPEN_CARD_COUNT);
    }

    public boolean isUnderDrawLimit() {
        return !getScore().isBigger(Score.from(DRAW_LIMIT_SCORE));
    }
}
