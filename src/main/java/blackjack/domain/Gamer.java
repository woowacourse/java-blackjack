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

    public void add(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return getTotalScore() > PLAYING_STANDARD;
    }

    public int getMinusScore(int targetScore) {
        return targetScore - getTotalScore();
    }

    abstract GameResult createResult(Gamer gamer);

    abstract boolean canHit();

    public String getName() {
        return name.getValue();
    }

    public Cards getCards() {
        return cards;
    }

}
