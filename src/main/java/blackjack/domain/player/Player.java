package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;

import java.util.Objects;

public abstract class Player {

    public static final int NUMBER_OF_INITIAL_CARDS = 2;

    protected final Name name;
    protected final Cards cards;
    protected Money money;

    protected Player(String name, Money money) {
        this.name = new Name(name);
        this.cards = new Cards();
        this.money = money;
    }

    public final void initializeCards(final Deck deck) {
        for (int i = 0; i < NUMBER_OF_INITIAL_CARDS; i++) {
            drawCard(deck);
        }
    }

    public final void drawCard(final Deck deck) {
        cards.add(deck.draw());
    }

    public final boolean isBust() {
        return cards.isBust();
    }

    public final boolean isHit() {
        return cards.isHit();
    }

    public final boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public final boolean isTwentyOne() {
        return cards.isTwentyOne();
    }

    public final boolean isSameName(Player player) {
        return Objects.equals(this.name(), player.name());
    }


    public final Cards cards() {
        return cards;
    }

    public final String name() {
        return name.getName();
    }

    public final Score score() {
        return cards.totalScore();
    }

    public final Money money() {
        return money;
    }
}
