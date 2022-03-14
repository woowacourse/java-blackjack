package blackjack.domain.player;

import blackjack.domain.HitFlag;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends AbstractPlayer {
    private static final String DEALER_NAME = "딜러";
    private static final int HIT_FLAG_SCORE = 16;
    private static final int FIRST_CARD_INDEX = 0;

    public Dealer() {
        super(DEALER_NAME, new Cards(new ArrayList<>()));
    }

    @Override
    public Cards getShowCards() {
        return new Cards(List.of(getCards().getCardValues().get(FIRST_CARD_INDEX)));
    }

    @Override
    public HitFlag checkHitFlag() {
        if (getCards().calculateScore() <= HIT_FLAG_SCORE) {
            return HitFlag.Y;
        }
        return HitFlag.N;
    }
}
