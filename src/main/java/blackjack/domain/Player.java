package blackjack.domain;

import java.util.Objects;

public class Player {

    private static final int BLACK_JACK_SCORE = 21;
    private static final int ACE_ALTER_VALUE = 10;

    private final Name name;
    private final Cards cards;

    public Player(Name name) {
        this.name = name;
        this.cards = new Cards();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int sum = cards.sum();
        int aceCount = cards.getAceCount();

        while (sum > BLACK_JACK_SCORE && aceCount > 0) {
            sum -= ACE_ALTER_VALUE;
            aceCount -= 1;
        }
        return sum;
    }

    public Cards getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
