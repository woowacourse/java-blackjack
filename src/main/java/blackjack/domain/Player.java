package blackjack.domain;

public class Player {

    private final Name name;
    private final Cards cards;

    public Player(final String name, final Cards cards) {
        this.name = new Name(name);
        this.cards = cards;
    }

    public void hit(final Card card) {
        cards.addCard(card);
    }

    public boolean isHittable() {
        return !cards.isMaximumScore() && !cards.isTotalScoreOver();
    }

    public int getScore() {
        return cards.calculateTotalScore();
    }

    public String getName() {
        return name.getValue();
    }

    public Cards getCards() {
        return cards;
    }
}
