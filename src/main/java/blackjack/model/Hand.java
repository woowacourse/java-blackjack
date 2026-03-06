package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;
    private final AceAdjustPolicy aceAdjustPolicy;

    public Hand(AceAdjustPolicy aceAdjustPolicy) {
        this.cards = new ArrayList<>();
        this.aceAdjustPolicy = aceAdjustPolicy;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public int calculateScore() {
        int score = cards.stream()
                .mapToInt(Card::getValue)
                .sum();

        return aceAdjustPolicy.adjust(score, cards);
    }
}
