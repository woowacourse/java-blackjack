package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Cards {

    private static final int BLACKJACK = 21;

    private final Stack<Card> cards = new Stack<>();

    public void generate() {
        cards.addAll(Card.getTotalCard());
        Collections.shuffle(cards);
    }

    public Card pick() {
        return cards.pop();
    }

    public void add(final List<Card> initCards) {
        cards.addAll(initCards);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int calculateTotal() {
        List<CardNumber> cardNumbers = cards.stream()
                .map(Card::getCardNumber)
                .collect(Collectors.toUnmodifiableList());
        return CardNumber.getTotal(cardNumbers);
    }

    public boolean isOverBlackjack() {
        return calculateTotal() > BLACKJACK;
    }

    public boolean isUnderBlackjack() {
        return calculateTotal() < BLACKJACK;
    }

    public List<Card> getPartOfDealerCard() {
        return List.of(cards.firstElement());
    }

    public List<Card> getAllCards() {
        return cards.stream()
                .collect(Collectors.toUnmodifiableList());
    }
}
