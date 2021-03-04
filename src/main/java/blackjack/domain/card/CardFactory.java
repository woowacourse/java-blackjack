package blackjack.domain.card;

import java.util.Stack;

public class CardFactory {

    private CardFactory() {
    }

    public static Stack<Card> create() {
        Stack<Card> cards = new Stack<>();
        for (Type type : Type.values()) {
            for (Denomination denomination : Denomination.values()) {
                cards.push(new Card(type, denomination));
            }
        }
        return cards;
    }

}
