package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Gamer {

    private static final int MAX_SCORE = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;

    private final PlayerName playerName;
    private final Cards cards;

    public Gamer(PlayerName playerName) {
        this.playerName = playerName;
        this.cards = new Cards(new ArrayList<>());
    }

    public void hit(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return getScore() > MAX_SCORE;
    }

    public boolean isBLACKJACK() {
        if (cards.get().size() != BLACKJACK_CARD_COUNT || !cards.containsCardNumber(CardNumber.ACE)) {
            return false;
        }
        return cards.containsCardNumber(CardNumber.JACK)
                || cards.containsCardNumber(CardNumber.QUEEN)
                || cards.containsCardNumber(CardNumber.KING);
    }

    public PlayerName getName() {
        return playerName;
    }

    public Cards getCards() {
        return cards;
    }

    public int getScore() {
        return cards.getTotalScore();
    }

    public abstract boolean isValidRange();

    public abstract int compare(Gamer o);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Gamer)) {
            return false;
        }
        Gamer gamer = (Gamer) o;
        return Objects.equals(playerName, gamer.playerName) && Objects.equals(cards, gamer.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, cards);
    }

    @Override
    public String toString() {
        return "Gamer{" +
                "name=" + playerName +
                ", cards=" + cards +
                '}';
    }
}
