package domain.participant;

import domain.money.Money;
import domain.card.Card;
import domain.card.Cards;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/08
 */
public abstract class Participant {

    protected final Cards cards;
    protected Money money;

    protected Participant(final Money money, final Cards cards) {
        this.cards = cards;
        this.money = money;
    }

    public int getMoney() {
        return this.money.getMoney();
    }

    public boolean addCard(final Card card) {
        return cards.addCard(card);
    }

    public int sumOfCards() {
        return cards.sumOfCards();
    }

    public Cards getCards() {
        return cards;
    }

    public boolean isNotBurst() {
        return cards.isNotBurst();
    }
}
