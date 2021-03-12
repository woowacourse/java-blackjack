package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.game.WinOrLose;

import java.util.Objects;

public class Gambler implements Player {

    private final Name name;
    private final Cards cards;
    private Money money;

    public Gambler(final String name, final Money money) {
        this.name = new Name(name);
        this.cards = new Cards();
        this.money = money;
    }

    @Override
    public void initializeCards(final Deck deck) {
        for (int i = 0; i < NUMBER_OF_INITIAL_CARDS; i++) {
            cards.add(deck.draw());
        }
    }

    public void calculateProfit(WinOrLose winOrLose) {
        money = winOrLose.calculateProfit(money);
    }

    public Money inverseMoney() {
        return money.inverseMoney();
    }

    @Override
    public void drawCard(final Deck deck) {
        cards.add(deck.draw());
    }

    @Override
    public boolean isBust() {
        return cards.isBust();
    }

    @Override
    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    @Override
    public boolean isTwentyOne() {
        return cards.isTwentyOne();
    }

    @Override
    public boolean isSameName(Player player) {
        return Objects.equals(this.name(), player.name());
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public String name() {
        return name.getName();
    }

    @Override
    public Score score() {
        return cards.totalScore();
    }

    @Override
    public Money money() {
        return money;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
