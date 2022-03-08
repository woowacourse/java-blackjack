package blackjack.domain;

public class Card {

    private final String pattern;
    private final String denomination;

    public Card(String pattern, String denomination) {
        this.pattern = pattern;
        this.denomination = denomination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Card card = (Card) o;

        if (!pattern.equals(card.pattern)) {
            return false;
        }
        return denomination.equals(card.denomination);
    }

    @Override
    public int hashCode() {
        int result = pattern.hashCode();
        result = 31 * result + denomination.hashCode();
        return result;
    }
}
