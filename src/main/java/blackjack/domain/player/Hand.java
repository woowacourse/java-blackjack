package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.result.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        this(Collections.emptyList());
    }

    public Hand(Card... cards) {
        this(List.of(cards));
    }

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        Score score = score();
        if (hasAce()) {
            return score.plusTenIfNotBust();
        }
        return score;
    }

    public boolean isBlackJack() {
        return calculateScore().isBlackJack() && cards.size() == 2;
    }

    private Score score() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(Score.min(), Score::add);
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return cards;
    }
}
