package blackjack.domain.player;

import blackjack.domain.card.CardDeck;

public class Gambler extends Player {

    private static final int DEFAULT_INIT_SIZE = 2;

    private final double money;

    private Gambler(Name name, double money, CardDeck cardDeck) {
        super(name, cardDeck);
        this.money = money;
    }

    public static Gambler of(Name name, double money, CardDeck cardDeck) {
        return new Gambler(name, money, cardDeck);
    }

    public boolean isFirstQuestion() {
        return getState().cards().size() <= DEFAULT_INIT_SIZE;
    }

    public double getMoney() {
        return money;
    }

    @Override
    public boolean isHit() {
        return !getState().isFinished();
    }
}
