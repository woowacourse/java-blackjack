package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;

public class Gambler implements Player {

    private static final int NUMBER_OF_INITIAL_CARDS = 2;

    private final Name name;
    private final Cards cards;
    private int money;

    public Gambler(final Name name) {
        this.name = name;
        this.cards = new Cards();
    }

    public void bet(int money) {
        this.money += money;
    }

    @Override
    public void receiveCard(final Card card) {
        cards.add(card);
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Score getScore() {
        return cards.calculateScore();
    }

    @Override
    public Cards getCards() {
        return cards;
    }
}
