package blackjack.player;

import blackjack.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    Hand(List<Card> cards) {
        this.cards = cards;
    }

    Hand() {
        this(new ArrayList<>());
    }

    public Score calculateScore() {
        Score minimumScore = calculateMinimumScore();
        if (!hasAce()) {
            return minimumScore;
        }
        return minimumScore.changeToLargeAceScore();
    }

    private Score calculateMinimumScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(new Score(0), Score::add);
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
