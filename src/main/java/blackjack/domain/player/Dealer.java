package blackjack.domain.player;

import blackjack.domain.card.CardDeck;

public class Dealer extends Player {

    private static final int AVAILABLE_POINT_FOR_ADD_CARD = 16;
    private static final String NAME = "딜러";

    private Dealer(CardDeck cardDeck) {
        super(Name.of(NAME), cardDeck);
    }

    public static Dealer of(CardDeck cardDeck) {
        return new Dealer(cardDeck);
    }

    @Override
    public boolean isHit() {
        return getPoint() <= AVAILABLE_POINT_FOR_ADD_CARD || !getState().isFinished();
    }
}
