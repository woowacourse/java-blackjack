package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.Objects;

public abstract class Participant {
    protected Nickname nickname;
    protected Cards cards;

    protected Participant(Nickname nickname, Cards cards) {
        this.nickname = nickname;
        this.cards = cards;
    }

    public abstract boolean canDraw();

    public final void receiveCard(Card card) {
        cards.addCard(card);
    }

    public final Cards getCurrentCards() {
        return cards;
    }

    public final Nickname getName() {
        return nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(nickname, that.nickname) && Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, cards);
    }
}
