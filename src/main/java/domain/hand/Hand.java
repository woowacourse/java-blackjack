package domain.hand;

import domain.card.Card;

import java.util.List;

public class Hand {
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Score getScore() {
        return new Score(ScoreCalculator.calculate(List.copyOf(cards)));
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public boolean hasScoreLessThanOrEqual(int value) {
        return getScore().value() <= value;
    }

    public boolean isBlackjack(){
        return cards.size() == 2 && getScore().value()==21;
    }

}
