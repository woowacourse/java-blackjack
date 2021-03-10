package blackjack.state;

import blackjack.domain.card.Card;

import java.util.List;

public class Cards {
    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return this.cards.stream().mapToInt(Card::getScore).sum() > Score.BLACKJACK_SCORE;
    }

    public Score score() {
        return checkAce(cardsScoreSum());
    }

    private Score cardsScoreSum() {
        return new Score(this.cards.stream()
                .mapToInt(Card::getScore)
                .sum());
    }

    private Score checkAce(Score score) {
        if (this.cards.stream()
                .anyMatch(Card::isAce) && score.isChangeAceNumber()) {
            return new Score(score.aceNumberChange());
        }
        return score;
    }
}
