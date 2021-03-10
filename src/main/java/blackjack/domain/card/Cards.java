package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cards {
    private final List<Card> cards;

    public Cards(Card... cards) {
        this(Arrays.asList(cards));
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Score scores() {
        Score score = sum();
        int aceNumbers = numberOfAces();
        for (int i = 0; i < aceNumbers; i++) {
            score = score.plusTenIfNotBust();
        }
        return score;
    }

    private int numberOfAces() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private Score sum() {
        return new Score(cards.stream()
                .mapToInt(Card::score)
                .sum());
    }

    public boolean isBlackjack() {
        return scores().isBlackjack();
    }

    public boolean isBust() {
        return scores().isBust();
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
