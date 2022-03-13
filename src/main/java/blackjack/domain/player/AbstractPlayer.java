package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.Objects;

public abstract class AbstractPlayer implements Player {

    private static final int BLACKJACK_CARD_COUNT = 2;

    private static final int MAX_SCORE = 21;

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
    public boolean isDealer() {
        return false;
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
    public boolean isValidRange() {
        return getScore() < MAX_SCORE;
    }

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
