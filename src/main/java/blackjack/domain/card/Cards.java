package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void add(Cards targetCards) {
        cards.addAll(targetCards.cards);
    }

    public int calculateFinalScore() {
        int score = calculateScoreWhenAceIsMinimum();
        int aceCounts = calculateAceCounts();
        int aceBonusScore = Symbol.ACE.calculateAceBonusScore(score, aceCounts);
        return score + aceBonusScore;
    }

    public int calculateScoreWhenAceIsMinimum() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int calculateAceCounts() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
