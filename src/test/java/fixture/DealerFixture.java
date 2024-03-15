package fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Hand;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Dealer2;
import java.util.ArrayList;
import java.util.List;

public class DealerFixture {

    public static Dealer createDealer() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardRank.SEVEN, CardSuit.CLUB));
        cards.add(new Card(CardRank.KING, CardSuit.SPADE));
        Hand hand = new Hand(cards);
        return new Dealer(hand);
    }

    public static Dealer2 createDealer2() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardRank.THREE, CardSuit.CLUB));
        cards.add(new Card(CardRank.NINE, CardSuit.SPADE));
        cards.add(new Card(CardRank.EIGHT, CardSuit.DIAMOND));
        Hand hand = new Hand(cards);
        return new Dealer2(hand);
    }
}
