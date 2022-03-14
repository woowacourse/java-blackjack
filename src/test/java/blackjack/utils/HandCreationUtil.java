package blackjack.utils;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Hand;
import blackjack.domain.card.strategy.FixedCardsGenerateStrategy;

public class HandCreationUtil {

    private HandCreationUtil() {
    }

    public static Hand createHand(Denomination... denominations) {
        FixedCardsGenerateStrategy cardsGenerateStrategy = new FixedCardsGenerateStrategy(denominations);
        Hand hand = new Hand();
        hand.addAll(cardsGenerateStrategy.generate().toArray(new Card[0]));
        return hand;
    }
}
