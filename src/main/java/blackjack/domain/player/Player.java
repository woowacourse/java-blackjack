package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Player {
    protected final Cards hand;
    private final Name name;
    private BetMoney betMoney;

    public Player(final Cards cards, final Name name) {
        this.hand = new Cards(cards.getList());
        this.name = new Name(name.value());
        betMoney = BetMoney.getInitMoney();
    }

    public abstract List<Card> getInitCards();

    public void receiveMoreCard(final Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand.getList();
    }

    public void betting(final BetMoney betMoney) {
        this.betMoney = new BetMoney(betMoney.toInt());
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public int getBetMoney() {
        return betMoney.toInt();
    }

    public int getScore() {
        return hand.getScore();
    }

    public String getName() {
        return name.value();
    }
}
