package blakcjack.domain.participant;

import blakcjack.domain.card.Card;
import blakcjack.domain.card.Cards;
import blakcjack.domain.money.Money;
import blakcjack.domain.name.Name;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
    protected final Name name;
    protected final Money money;
    protected final Cards cards;

    protected Participant(final String name, final double money) {
        this.name = new Name(name);
        this.money = new Money(money);
        this.cards = new Cards(Collections.emptyList());
    }

    public abstract ParticipantType getType();

    public void receiveCard(final Card card) {
        cards.add(card);
    }

    public boolean canDrawMoreCard() {
        return cards.isLowerThanBlackJack();
    }

    public List<Card> showCardList() {
        return cards.toList();
    }

    public int showScore() {
        return cards.calculateScore().toInt();
    }

    public String getNameValue() {
        return name.getName();
    }

    public int getMoneyValue() {
        return money.toInt();
    }

    protected boolean isBust() {
        return cards.isBust();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Participant that = (Participant) o;
        return Objects.equals(name, that.name) && Objects.equals(money, that.money) && Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money, cards);
    }
}
