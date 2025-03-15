package domain.gamer;

import domain.deck.Card;
import java.util.List;
import java.util.Objects;

public abstract class Gamer {

    private final Nickname nickname;
    private final Hand hand;

    public Gamer(final Nickname nickname) {
        this.nickname = nickname;
        this.hand = new Hand();
    }

    public abstract int calculateSumOfRank();

    public boolean isBust() {
        return hand.isBust();
    }

    public void receiveInitialCards(final List<Card> cards) {
        cards.forEach(hand::add);
    }

    public void hit(final Card card) {
        hand.add(card);
    }

    public String getDisplayName() {
        return nickname.getDisplayName();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    protected Hand getHand() {
        return hand;
    }

    public Nickname getNickname() {
        return nickname;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Gamer gamer)) {
            return false;
        }
        return Objects.equals(nickname, gamer.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nickname);
    }
}

