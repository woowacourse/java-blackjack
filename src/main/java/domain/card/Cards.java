package domain.card;

import java.util.ArrayList;
import java.util.List;

public final class Cards {

    private final List<Card> cards;
    private Score score;

    private Cards(final int score) {
        this.cards = new ArrayList<>();
        this.score = Score.from(score);
    }

    public static Cards from(final int score) {
        return new Cards(score);
    }

    public void takeCard(final Card card) {
        cards.add(card);
        sumScore();
    }

    private void sumScore() {
        final int newScore = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        score = Score.from(newScore);
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(this::isAce);
    }

    private boolean isAce(Card card) {
        return card.getRank() == Rank.ACE;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Score getScore() {
        if (hasAce() && score.canAddBonusScore()) {
            return score.getScoreWithBonusScore();
        }
        return score.getScore();
    }
}
