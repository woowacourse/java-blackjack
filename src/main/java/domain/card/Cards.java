package domain.card;

import java.util.ArrayList;
import java.util.List;

public final class Cards {

    private final List<Card> cards;
    private final Score score;

    private Cards(final int score) {
        this.cards = new ArrayList<>();
        this.score = Score.from(score);
    }

    public static Cards from(final int score) {
        return new Cards(score);
    }

    public void takeCard(final Card card) {
        cards.add(card);
        score.sumScore(card);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int getScore() {
        return score.getScore();
    }
}
