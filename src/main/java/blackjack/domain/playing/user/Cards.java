package blackjack.domain.playing.user;

import blackjack.domain.playing.card.Card;
import blackjack.domain.playing.card.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int MAX_SCORE_NUMBER_TO_MAXIMIZE = 12;
    private static final Score ADDING_SCORE_TO_MAXIMIZE = new Score(10);

    private final List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards emptyCards() {
        return new Cards(new ArrayList<>());
    }

    public static Cards from(List<Card> cards) {
        return new Cards(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        Score score = sumScore();
        return maximize(score);
    }

    private Score sumScore() {
        Score score = Score.ZERO_SCORE;

        for (Card card : cards) {
            score = score.add(card.getScore());
        }
        return score;
    }

    private Score maximize(Score score) {
        if (score.isUnder(MAX_SCORE_NUMBER_TO_MAXIMIZE) && hasAce()) {
            return score.add(ADDING_SCORE_TO_MAXIMIZE);
        }
        return score;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isNotEmpty() {
        return !cards.isEmpty();
    }

    public int count() {
        return cards.size();
    }

    public List<Card> getCardS() {
        return Collections.unmodifiableList(cards);
    }

}