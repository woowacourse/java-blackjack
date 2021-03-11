package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public class Gambler implements Player {
    private final Name name;
    private final Cards cards = new Cards();
    private Money money = new Money(0);

    public Gambler(final Name name) {
        this.name = name;
    }

    public void earn(Money money) {
        this.money = this.money.add(money);
    }

    public void lose(Money money) {
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
    public boolean isBust() {
        return cards.isBust();
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
    public Score getScore() {
        return cards.calculateScore();
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
