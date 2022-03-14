package blackjack.domain;

public abstract class AbstractPlayer implements Player {

    protected Deck cards;
    protected String name;

    @Override
    public void addCard(Card card) {
        cards.addCard(card);
    }

    @Override
    public boolean isBlackJack() {
        return cards.sumBlackJack();
    }

    @Override
    public boolean isDealer() {
        return this.name.equals(Dealer.NAME);
    }

    @Override
    public boolean isLose(int point) {
        return point > cards.sumPoints();
    }

    @Override
    public boolean isOverPointLimit() {
        return cards.sumPoints() > Match.MAX_WINNER_POINT;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Deck getDeck() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractPlayer that = (AbstractPlayer) o;

        return cards != null ? cards.equals(that.cards) : that.cards == null;
    }

    @Override
    public int hashCode() {
        return cards != null ? cards.hashCode() : 0;
    }
}
