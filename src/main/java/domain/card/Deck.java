package domain.card;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private static final String KOREAN_REGEX = "[가-힣]+";

    private static final Stack<Card> deck = makeDeck();

    private static Stack<Card> makeDeck() {
        Stack<Card> stack = new Stack<>();
        for (Denomination denomination : Denomination.values()) {
            makeCard(stack, denomination);
        }
        Collections.shuffle(stack);
        return stack;
    }

    private static void makeCard(Stack<Card> stack, Denomination denomination) {
        for (Suit suit : Suit.values()) {
            stack.add(new Card(denomination.getName() + suit.getValue(), denomination.getValue()));
        }
    }

    public static Card generateCard() {
        return deck.pop();
    }

    public static int extractCardNumber(String card) {
        String cardValue = card.replaceAll(KOREAN_REGEX, "");
        return Denomination.convertNumber(cardValue);
    }
}
