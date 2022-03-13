package blackjack.domain.participant;

import blackjack.domain.card.Deck;

public class Player extends Participant {

    private Player(final String name, final Deck deck) {
        super(name, deck);
    }

    public static Player readyToPlay(final String name, final Deck deck) {
        return new Player(name, deck);
    }

    @Override
    public boolean isPossibleToDrawCard() {
        return isNotBlackjack() && isNotBust();
    }

    private boolean isNotBlackjack() {
        return !isBlackjack();
    }

    private boolean isNotBust() {
        return !isBust();
    }

}
