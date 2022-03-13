package blackjack.domain;

import java.util.ArrayList;
import java.util.Objects;

public abstract class AbstractPlayer implements Player {

    private static final int BLACKJACK_CARD_COUNT = 2;

    public static final int MAX_SCORE = 21;
    public static final int WIN = 1;
    public static final int DRAW = 0;
    public static final int LOSE = -1;

    private final Name name;
    private final Cards cards;

    public AbstractPlayer(Name name) {
        this.name = name;
        this.cards = new Cards(new ArrayList<>());
    }

    @Override
    public void hit(Card card) {
        cards.add(card);
    }

    @Override
    public boolean isBust() {
        return getScore() > MAX_SCORE;
    }

    @Override
    public boolean isBLACKJACK() {
        if (cards.get().size() != BLACKJACK_CARD_COUNT || !cards.containsCardNumber(CardNumber.ACE)) {
            return false;
        }
        return cards.containsCardNumber(CardNumber.JACK)
                || cards.containsCardNumber(CardNumber.QUEEN)
                || cards.containsCardNumber(CardNumber.KING);
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public int getScore() {
        return cards.getTotalScore();
    }

    @Override
    public abstract boolean isValidRange();

    @Override
    public abstract int compareWinning(Player o);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractPlayer)) {
            return false;
        }
        AbstractPlayer that = (AbstractPlayer) o;
        return Objects.equals(name, that.name) && Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }

    @Override
    public String toString() {
        return "AbstractPlayer{" +
                "name=" + name +
                ", cards=" + cards +
                '}';
    }
}
