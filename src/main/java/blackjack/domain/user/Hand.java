package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Hand {
    private static final int BLACK_JACK = 21;
    private static final int FIRST_INDEX = 0;
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Hand createEmptyHand() {
        return new Hand(Collections.emptyList());
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public int calculateScore() {
        int score = sumScore();

        while (score > BLACK_JACK && hasAce()) {
            decideCardValue();
            score = sumScore();
        }

        return score;
    }

    private void decideCardValue() {
        IntStream.range(FIRST_INDEX, cards.size())
                .filter(i -> isAce(cards.get(i)))
                .forEach(this::decideAceValue);
    }

    private Card decideAceValue(int i) {
        return cards.set(i, new Card(cards.get(i).getSuit(), cards.get(i).selectValue(sumScore())));
    }

    private boolean hasAce() {
        return cards.stream()
                .map(Card::getValue)
                .anyMatch(value -> value == Value.ACE);
    }

    private boolean isAce(Card card) {
        return card.getValue() == Value.ACE;
    }

    private int sumScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public Card getFirstCard() {
        return cards.get(FIRST_INDEX);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
