package domain.gamer;

import domain.card.CardDeck;
import domain.card.Hand;
import domain.rule.BlackjackMatchResult;
import domain.state.started.finished.Stay;

public class Dealer extends Gamer {

    private static final String NAME = "딜러";
    private static final int HIT_THRESHOLD = 16;

    public Dealer(Hand hand) {
        super(hand);
        checkState(hand);
    }

    private void checkState(Hand hand) {
        if (hand.calculateTotalPoint() > HIT_THRESHOLD) {
            state = new Stay(hand);
        }
    }

    public BlackjackMatchResult determineMatchResultFor(Player player) {
        return getHand().determineMatchResultFor(player.getHand());
    }

    @Override
    public String getNickname() {
        return NAME;
    }

    @Override
    public void hit(CardDeck deck) {
        super.hit(deck);
    }
}
