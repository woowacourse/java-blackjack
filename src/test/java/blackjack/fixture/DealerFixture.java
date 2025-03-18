package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import java.util.List;

public class DealerFixture {

    public static Dealer create(List<Card> cards) {
        Dealer dealer = new Dealer();
        dealer.addInitialCards(cards);
        return dealer;
    }

    public static Dealer createLessThanBlackjack() {
        Dealer dealer = new Dealer();
        List<Card> bustCard = CardFixture.makeLessThanBlackjack();
        dealer.addInitialCards(bustCard);
        return dealer;
    }

    public static Dealer createBust() {
        Dealer dealer = new Dealer();
        List<Card> bustCard = CardFixture.makeBust();
        dealer.addInitialCards(bustCard);
        return dealer;
    }

    public static Dealer createBlackJackWithFinalHand() {
        Dealer dealer = new Dealer();
        List<Card> bustCard = CardFixture.makeBlackjackOverTwoCard();
        dealer.addInitialCards(bustCard);
        return dealer;
    }
}
