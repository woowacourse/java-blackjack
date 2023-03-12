package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

import static blackjack.view.OutputView.DEALER_NAME;

public class Dealer extends Participant {

    private static final int MAX_SCORE_TO_RECEIVE = 16;

    public Dealer(List<Card> cards) {
        super(cards);
    }

    @Override
    public boolean isAbleToReceive() {
        return hand.calculateScore().isNotOver(MAX_SCORE_TO_RECEIVE);
    }

    @Override
    public void hit(Card card) {
        hand.add(card);
    }

    public String getName() {
        return DEALER_NAME;
    }
}
