package blackjack.domain.card;

import blackjack.domain.result.Score;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardGroup {

    private final List<Card> cards;

    private CardGroup(final List<Card> cards) {
        this.cards = cards;
    }

    public CardGroup(final Card... cards) {
        this.cards = new ArrayList<>();
        Collections.addAll(this.cards, cards);
    }

    public void add(final Card newCard) {
        cards.add(newCard);
    }

    public Score getScore() {
        return Score.calculateScore(getTotalValue(), getAceCount(), cards.size());
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

    public boolean isBustScore() {
        return getScore().isBust();
    }

    public CardGroup getSubCardGroup(int size) {
        final List<Card> subCardGroup = cards.stream()
                .limit(size)
                .collect(Collectors.toUnmodifiableList());
        return new CardGroup(subCardGroup);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
