package blackjack.domain;

public abstract class AbstractPlayer implements Player {

    protected Deck cards;
    protected String name;

    @Override
    public void addCard(Card card) {
        cards.addCard(card);
    }

    @Override
    public boolean isOverLimit(int limit) {
        return cards.sumPoints() > limit;
    }

    @Override
    public boolean isDealer() {
        return this.name.equals(Dealer.NAME);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Deck getDeck() {
        return cards;
    }
}
