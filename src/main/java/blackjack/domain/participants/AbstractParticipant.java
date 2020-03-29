package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participants.money.Money;

public abstract class AbstractParticipant implements Participant {

    protected final Cards cards;
    protected Money money;
    protected String name;

    public AbstractParticipant() {
        this.cards = new Cards();
        this.money = new Money();
    }

    @Override
    public abstract void drawMoreCard(final Deck deck);

    @Override
    public abstract boolean isDealer();

    @Override
    public void draw(final Deck deck) {
        cards.add(deck.pop());
    }

    // 테스트용
    public void draw(final Card card) {
        cards.add(card);
    }

    @Override
    public int score() {
        return cards.calculate();
    }

    @Override
    public int cardCount() {
        return cards.size();
    }

    @Override
    public String cards() {
        return cards.toString();
    }

    @Override
    public void bet(final String amount) {
        this.money = new Money(amount);
    }

    @Override
    public void earn(final Money money) {
        this.money.add(money);
    }

    @Override
    public void lose(final Money money) {
        this.money.subtract(money);
    }

    @Override
    public void loseAll() {
        money.getOpposite();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Money getMoney() {
        return money;
    }
}
