package blackjack.player.state;

import blackjack.card.Card;
import blackjack.player.Hand;
import java.util.List;

public abstract class PlayerState {

    private static final int DEALER_MAX_HIT_SCORE = 16;

    protected Hand hand;

    protected PlayerState(Hand hand) {
        this.hand = hand;
    }

    public static PlayerState createPlayerStateWithCardsOf(Card first, Card second) {
        Hand hand = new Hand(List.of(first, second));
        if (hand.isBlackJack()) {
            return new BlackJackState(hand);
        }
        return new HitState(hand);
    }

    public static PlayerState createDealerStateWithCardsOf(Card first, Card second) {
        PlayerState state = createPlayerStateWithCardsOf(first, second);
        Hand dealerHand = state.hand;

        if (dealerHand.hasScoreGreaterThan(DEALER_MAX_HIT_SCORE)) {
            return new StandState(dealerHand);
        }
        return new HitState(dealerHand);
    }

    public abstract PlayerState drawCard(Card card);

    public abstract PlayerState stand();

    public List<Card> cards() {
        return hand.getCards();
    }
}
