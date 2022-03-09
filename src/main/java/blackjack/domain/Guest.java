package blackjack.domain;

public class Guest extends AbstractPlayer implements Player {

    public Guest(String name) {
        this.cards = new Deck();
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Guest guest = (Guest) o;

        return cards != null ? cards.equals(guest.cards) : guest.cards == null;
    }

    @Override
    public int hashCode() {
        return cards != null ? cards.hashCode() : 0;
    }
}
