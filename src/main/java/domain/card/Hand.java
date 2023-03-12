package domain.card;

import domain.user.Score;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Hand add(Card card) {
        List<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);
        return new Hand(newCards);
    }

    public Score calculateScore() {
        Score sumScore = sum();
        if(sumScore.isOverMax()) {
            return sumScore.calculateAceAsOne(countOfAce());
        }
        return sumScore;
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isBust() {
        return calculateScore().isOverMax();
    }

    private Score sum() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(new Score(0), Score::add);
    }

    private int countOfAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }
}
