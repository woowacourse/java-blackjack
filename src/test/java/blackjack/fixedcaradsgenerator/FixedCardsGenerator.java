package blackjack.fixedcaradsgenerator;

import java.util.List;
import java.util.Stack;

import card.Card;
import deck.CardsGenerator;

public class FixedCardsGenerator implements CardsGenerator {
    private final List<Card> cardCandidates;

    public FixedCardsGenerator(List<Card> cardCandidates) {
        this.cardCandidates = cardCandidates;
    }

    @Override
    public Stack<Card> generate() {
        Stack<Card> cards = new Stack<>();
        cards.addAll(cardCandidates);
        return cards;
    }
}
