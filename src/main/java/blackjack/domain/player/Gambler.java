package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;

public class Gambler implements Player {

    private final Name name;
    private final Cards cards;

    public Gambler(final String name) {
        this.name = new Name(name);
        this.cards = new Cards();
    }

    @Override
    public void initializeCards(final Deck deck) {
        for (int i = 0; i < NUMBER_OF_INITIAL_CARDS; i++) {
            cards.add(deck.draw());
        }
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
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
