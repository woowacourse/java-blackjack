package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.user.Hand;

public class HandFixtures {

    public static final Hand STAY_HAND_15 = createHand(CardFixtures.FIVE, CardFixtures.TEN);
    public static final Hand STAY_HAND_17 = createHand(CardFixtures.SEVEN, CardFixtures.TEN);
    public static final Hand BLACKJACK_HAND = createHand(CardFixtures.ACE, CardFixtures.TEN);
    public static final Hand BUST_HAND = createHand(CardFixtures.FIVE, CardFixtures.TEN, CardFixtures.JACK);

    private static Hand createHand(Card ... cards) {
        Hand hand = new Hand();
        for (Card card : cards) {
            hand = hand.addCard(card);
        }
        return hand;
    }

}
