package blackjack.domain;

import java.util.List;

public abstract class Gamer {

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

    abstract GameResult createResult(int targetScore);

    public String getName() {
        return name.getValue();
    }

    public Cards getCards() {
        return cards;
    }
}
