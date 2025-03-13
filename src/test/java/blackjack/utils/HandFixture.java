package blackjack.utils;

import blackjack.domain.Card;
import blackjack.domain.CardRank;
import blackjack.domain.CardSuit;
import blackjack.domain.Hand;

public final class HandFixture {

    private HandFixture() {
    }

    public static Hand blackjack() {
        Card card1 = new Card(CardSuit.SPADE, CardRank.ACE);
        Card card2 = new Card(CardSuit.SPADE, CardRank.KING);

        return Hand.of(card1, card2);
    }

    public static Hand busted() {
        Card card = new Card(CardSuit.SPADE, CardRank.KING);
        Hand hand = Hand.of(card, card);
        hand.takeCard(card);

        return hand;
    }

    public static Hand normal() {
        Card card = new Card(CardSuit.SPADE, CardRank.KING);

        return Hand.of(card, card);
    }

    public static Hand createHandWithOptimisticValue20() {
        Card card = new Card(CardSuit.HEART, CardRank.KING);
        return Hand.of(card, card);
    }

    public static Hand createHandWithOptimisticValue15() {
        Card card1 = new Card(CardSuit.HEART, CardRank.KING);
        Card card2 = new Card(CardSuit.SPADE, CardRank.FIVE);
        return Hand.of(card1, card2);
    }

    public static Hand createHandWithOptimisticValue21() {
        Card card1 = new Card(CardSuit.HEART, CardRank.KING);
        Card card2 = new Card(CardSuit.SPADE, CardRank.FIVE);
        Card card3 = new Card(CardSuit.SPADE, CardRank.SIX);

        Hand hand = Hand.of(card1, card2);
        hand.takeCard(card3);

        return hand;
    }
}
