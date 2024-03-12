package blackjack.domain.card;

import blackjack.domain.rule.Score;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public void append(Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        Score score = sumScore();
        if (hasAce()) {
            return score.adjustAceScore();
        }
        return score;
    }

    private Score sumScore() {
        return cards.stream()
                .map(card -> new Score(card.getCardRank().getScore()))
                .reduce(new Score(0), Score::add);
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
