package blackjack.domain.card.status;

import blackjack.domain.card.Card;

public interface HandStatus {

    HandStatus add(Card card);

    SingleMatchResult matchAtPlayer(HandStatus dealerHand);

    SingleMatchResult matchAtDealer(HandStatus playerHand);
}
