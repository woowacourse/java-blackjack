package fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.hand.Hand;
import blackjack.domain.dealer.Dealer;
import java.util.ArrayList;
import java.util.List;

public class DealerFixture {

    public static Dealer createDealer() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardRank.THREE, CardSuit.CLUB));
        cards.add(new Card(CardRank.NINE, CardSuit.SPADE));
        cards.add(new Card(CardRank.EIGHT, CardSuit.DIAMOND));
        Hand hand = new Hand(cards);
        return new Dealer(hand);
    }
}
