package blackjack.fixedCaradsGenerator;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Pattern;
import blackjack.domain.deck.CardsGenerator;
import java.util.Stack;

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
