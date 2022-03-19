package blackjack.domain.player;

import blackjack.domain.card.CardDeck;

public class Gambler extends Player {

    private Gambler(Name name, CardDeck cardDeck) {
        super(name, cardDeck);
    }

    public static Gambler of(Name name, CardDeck cardDeck) {
        return new Gambler(name, cardDeck);
    }

    public boolean isFirstQuestion() {
        return getState().cards().size() <= 2;
    }

    @Override
    public boolean isHit() {
        return !getState().isFinished();
    }
}
