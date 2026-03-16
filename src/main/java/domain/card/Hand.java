package domain.card;

import domain.Score;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int BLACKJACK_CARD_COUNT = 2;
    private final List<Card> cards;

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand of(List<Card> cards) {
        return new Hand(cards);
    }

    public static Hand copyOf(Hand hand) {
        return new Hand(new ArrayList<>(hand.cards));
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Card peek() {
        return cards.getFirst();
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_COUNT && totalSum().isBlackjack();
    }

    public boolean isBust() {
        return totalSum().isBust();
    }

    public Score totalSum() {
        return getSumWithoutAce().sumWithAce(getAceAmount());
    }

    private int getAceAmount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private Score getSumWithoutAce() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .map(card -> card.getRank().getScore())
                .reduce(Score.ZERO, Score::add);
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
