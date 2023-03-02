package fixedCaradsGenerator;

import java.util.Stack;

import card.Card;
import card.CardNumber;
import card.Pattern;
import deck.CardsGenerator;

public class FixedCardsGenerator implements CardsGenerator {
    @Override
    public Stack<Card> generate() {
        Stack<Card> cards = new Stack<>();
        cards.push(new Card(CardNumber.ACE, Pattern.HEART));
        cards.push(new Card(CardNumber.ACE, Pattern.CLOVER));
        cards.push(new Card(CardNumber.ACE, Pattern.DIAMOND));
        cards.push(new Card(CardNumber.ACE, Pattern.SPADE));
        return cards;
    }
}
