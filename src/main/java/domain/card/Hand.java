package domain.card;

import domain.Rank;
import domain.Score;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int BLACKJACK_CARD_COUNT = 2;
    private final List<Card> cards;

    private Hand() {
        this.cards = new ArrayList<>();
    }

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand createEmpty() {
        return new Hand();
    }

    public static Hand copyOf(Hand hand) {
        return new Hand(List.copyOf(hand.getCards()));
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void addAll(List<Card> cards) {
        this.cards.addAll(cards);
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

    public int size() {
        return cards.size();
    }

    public Score totalSum() {
        return Rank.sumWithAce(getAceAmount(), getSumWithoutAce());
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

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
