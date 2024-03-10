package blackjack.domain.player;

import blackjack.domain.card.CardDeck;
import blackjack.domain.rule.DealerHitStrategy;
import blackjack.domain.rule.PlayerHitStrategy;
import blackjack.domain.rule.ScoreCalculateStrategy;

public class HandCreator {

    private static final int INITIAL_HAND_SIZE = 2;

    public Hand createPlayerHandFrom(CardDeck cardDeck) {
        return new Hand(cardDeck.popCards(INITIAL_HAND_SIZE), new ScoreCalculateStrategy(), new PlayerHitStrategy());
    }

    public Hand createDealerHandFrom(CardDeck cardDeck) {
        return new Hand(cardDeck.popCards(INITIAL_HAND_SIZE), new ScoreCalculateStrategy(), new DealerHitStrategy());
    }

    public Hand createHand(CardDeck cardDeck) {
        return new Hand(cardDeck.popCards(INITIAL_HAND_SIZE), new ScoreCalculateStrategy());
    }
}
