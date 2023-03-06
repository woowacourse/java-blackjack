package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class CardGroup {

    private final List<Card> cards = new ArrayList<>();

    public CardGroup(final Card firstCard, final Card secondCard) {
        cards.add(firstCard);
        cards.add(secondCard);
    }

    public void add(final Card newCard) {
        cards.add(newCard);
    }

    public Score getScore() {
        return Score.calculateScore(getTotalValue(), getAceCount());
    }

    private int getTotalValue() {
        return cards.stream()
                .map(Card::getNumber)
                .mapToInt(CardNumber::getValue)
                .sum();
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

}
