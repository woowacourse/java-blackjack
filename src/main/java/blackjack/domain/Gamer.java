package blackjack.domain;

import java.util.List;

public class Gamer {

    protected static final int PLAYING_STANDARD = 21;

    private final Name name;
    private final Cards cards;

    public Gamer(String name, List<Card> cards) {
        this.name = new Name(name);
        this.cards = new Cards(cards);
    }

    public int getTotalScore() {
        return cards.calculateTotalScore();
    }

    public void combine(Card card) {
        cards.combine(card);
    }

    public GameResult createResult(int targetScore) {
        if (getTotalScore() > PLAYING_STANDARD) {
            return GameResult.LOSE;
        }

        if (targetScore > PLAYING_STANDARD) {
            return GameResult.WIN;
        }
        return GameResult.of(getTotalScore() - targetScore);
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return cards.getValue();
    }
}
