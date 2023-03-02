package deck;

import java.util.Stack;

import card.Card;
import card.CardNumber;
import card.Pattern;

public class ShuffledCardsGenerator implements CardsGenerator {
    @Override
    public Stack<Card> generate() {
        Stack<Card> cardStack = new Stack<>();
        for (CardNumber cardNumber : CardNumber.values()) {
            for (Pattern cardPattern : Pattern.values()) {
                cardStack.push(new Card(cardNumber, cardPattern));
            }
        }
        return cardStack;
    }
}
