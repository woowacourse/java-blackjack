package deck;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import card.Card;
import card.CardNumber;
import card.Pattern;

public class ShuffledCardsGenerator implements CardsGenerator {
    @Override
    public Stack<Card> generate() {
        List<Card> cards = createCards();
        Collections.shuffle(cards);
        Stack<Card> cardStack = new Stack<>();
        cardStack.addAll(cards);
        return cardStack;
    }

    private List<Card> createCards() {
        return Arrays.stream(CardNumber.values())
                .flatMap(cardNumber ->
                        Arrays.stream(Pattern.values()).map(pattern -> new Card(cardNumber, pattern))
                ).collect(Collectors.toList());
    }
}
