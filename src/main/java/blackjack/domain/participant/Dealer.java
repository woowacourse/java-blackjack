package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Participant {

    private static final int MIN_SCORE_STANDARD = 16;

    public Dealer(String name) {
        super(name);
    }

    public boolean checkUnderScoreStandard() {
        return state.cardSum() <= MIN_SCORE_STANDARD;
    }

    public List<Card> getHoldingCardsWithoutHidden() {
        return state.holdingCards().getCardsWithOutHiddenCard();
    }
}
