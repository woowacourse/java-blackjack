package blackjack.domain;

public class Dealer extends AbstractPlayer implements Player {

    public static final String NAME = "딜러";
    public static final int MAX_POINT = 16;
    public static final int EXCEED_POINT = 17;

    public Dealer() {
        this.cards = new Deck();
        this.name = NAME;
    }

    @Override
    public boolean isOverLimit() {
        return cards.sumPoints() > MAX_POINT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dealer dealer = (Dealer) o;

        return cards != null ? cards.equals(dealer.cards) : dealer.cards == null;
    }

    @Override
    public int hashCode() {
        return cards != null ? cards.hashCode() : 0;
    }
}
