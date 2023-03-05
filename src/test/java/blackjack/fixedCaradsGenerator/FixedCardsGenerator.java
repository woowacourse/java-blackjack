package blackjack.fixedCaradsGenerator;

import java.util.Stack;

import card.Card;
import card.Rank;
import card.Suit;
import deck.CardsGenerator;

public class FixedCardsGenerator implements CardsGenerator {
    @Override
    public Stack<Card> generate() {
        Stack<Card> cards = new Stack<>();
        cards.push(new Card(Rank.ACE, Suit.HEART));
        cards.push(new Card(Rank.ACE, Suit.CLOVER));
        cards.push(new Card(Rank.ACE, Suit.DIAMOND));
        cards.push(new Card(Rank.ACE, Suit.SPADE));
        return cards;
    }
}
