package fixture;

import domain.Card;
import domain.Dealer;
import java.util.List;

public class DealerFixture {
    public static Dealer createDefaultDealer() {
        return new Dealer();
    }

    public static Dealer createDealerWithCards(List<Card> cards) {
        Dealer dealer = createDefaultDealer();
        for (Card card : cards) {
            dealer.receiveCard(card);
        }
        dealer.calculateScore();
        return dealer;
    }
}
