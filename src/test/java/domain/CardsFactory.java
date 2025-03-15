package domain;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;

public class CardsFactory {

    public Card[] createBlackJackCards1() {
        return new Card[]{new Card(Denomination.JACK, Suit.CLUB),
                new Card(Denomination.ACE, Suit.CLUB)};
    }

    public Card[] createBlackJackCards2() {
        return new Card[]{new Card(Denomination.KING, Suit.CLUB),
                new Card(Denomination.ACE, Suit.DIAMOND)};
    }

    public Card[] createMaxScoreCards() {
        return new Card[]{new Card(Denomination.TWO, Suit.CLUB),
                new Card(Denomination.NINE, Suit.CLUB),
                new Card(Denomination.TEN, Suit.CLUB)};
    }

    public Card[] createScore18Cards() { //총합: 18
        return new Card[]{new Card(Denomination.TWO, Suit.CLUB),
                new Card(Denomination.QUEEN, Suit.CLUB),
                new Card(Denomination.SIX, Suit.CLUB)};
    }

    public Card[] createScore19Cards1() { //총합: 19
        return new Card[]{new Card(Denomination.THREE, Suit.CLUB),
                new Card(Denomination.QUEEN, Suit.DIAMOND),
                new Card(Denomination.SIX, Suit.DIAMOND)};
    }

    public Card[] createScore19Cards2() { //총합: 19
        return new Card[]{new Card(Denomination.TWO, Suit.DIAMOND),
                new Card(Denomination.JACK, Suit.DIAMOND),
                new Card(Denomination.SEVEN, Suit.DIAMOND)};
    }

    public Card[] createBustCardsWithNoAce() {
        return new Card[]{new Card(Denomination.TWO, Suit.CLUB),
                new Card(Denomination.TEN, Suit.CLUB),
                new Card(Denomination.JACK, Suit.DIAMOND)};
    }

    public Card[] createCanResolveBustCardsWithOneAce() {
        return new Card[]{new Card(Denomination.TWO, Suit.CLUB),
                new Card(Denomination.JACK, Suit.DIAMOND),
                new Card(Denomination.ACE, Suit.DIAMOND)};
    }

    public Card[] createCanResolveBustCardsWithTwoAce() {
        return new Card[]{new Card(Denomination.TEN, Suit.CLUB),
                new Card(Denomination.ACE, Suit.DIAMOND),
                new Card(Denomination.ACE, Suit.CLUB)};
    }

    public Card[] createCantResolveBustCardsWithTwoAce() {
        return new Card[]{new Card(Denomination.TEN, Suit.CLUB),
                new Card(Denomination.TEN, Suit.DIAMOND),
                new Card(Denomination.ACE, Suit.HEART),
                new Card(Denomination.ACE, Suit.DIAMOND)};
    }

    public Card[] createScore18CardsWithAce() {
        return new Card[]{new Card(Denomination.TWO, Suit.CLUB),
                new Card(Denomination.ACE, Suit.DIAMOND),
                new Card(Denomination.FIVE, Suit.HEART)};
    }

    public Card[] createScore19TwoCards() {
        return new Card[]{new Card(Denomination.NINE, Suit.CLUB),
                new Card(Denomination.JACK, Suit.DIAMOND)};
    }
}
