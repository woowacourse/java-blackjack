package fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.hand.Hand;

import java.util.ArrayList;
import java.util.List;

public class HandFixture {

    public static Hand createHandWithScoreTotal21() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardRank.TEN, CardSuit.HEART));
        cards.add(new Card(CardRank.NINE, CardSuit.HEART));
        cards.add(new Card(CardRank.TWO, CardSuit.HEART));
        return new Hand(cards);
    }

    public static Hand createHandWithScoreTotal16() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardRank.TEN, CardSuit.HEART));
        cards.add(new Card(CardRank.SIX, CardSuit.HEART));
        return new Hand(cards);
    }
}
