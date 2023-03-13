package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.dto.HandStatus;
import java.util.List;

public class Dealer extends Participant {

    public static final String NAME = "딜러";
    private static final int CARD_TAKE_LIMIT = 17;
    private static final int INITIAL_DEALER_CARD_OPEN_INDEX = 0;

    public Dealer() {
        super(NAME);
    }

    public Dealer(final List<Card> cards) {
        super(NAME, cards);
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
