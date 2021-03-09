package blackjack.domain.state;

import blackjack.domain.card.UserDeck;
import blackjack.domain.user.Dealer;

public class StateFactory {
    public static State draw(UserDeck userDeck, int userCriteria) {
        if (userDeck.isBlackJack()) {
            return new BlackJack(userDeck);
        }
        if (userCriteria == Dealer.DEALER_CRITERIA) {
            return new DealerHit(userDeck);
        }
        return new PlayerHit(userDeck);
    }
}
