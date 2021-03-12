package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Gambler implements Player {
    private final Name name;
    private final Cards cards = new Cards();
    private Money money = Money.emptyMoney;

    public Gambler(final Name name) {
        this.name = name;
    }

    public void earn(final Money money) {
        this.money = this.money.add(money);
    }

    public void lose(final Money money) {
        this.money = this.money.sub(money);
    }

    public Money getBettingMoney() {
        return money.abs();
    }

    @Override
    public int getMoneyValue(){
        return money.getValue();
    }

    @Override
    public void receiveCard(final Card card) {
        cards.add(card);
    }

    @Override
    public String getNameValue() {
        return name.getValue();
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public boolean hasBlackJack() {
        return cards.isBlackJack();
    }
}
