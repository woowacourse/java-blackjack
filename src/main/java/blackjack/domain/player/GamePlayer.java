package blackjack.domain.player;


import blackjack.domain.card.Cards;

public class GamePlayer extends Participant {
    private static final int RECEIVE_SIZE = 21;

    public GamePlayer(final Name name, final Cards cards) {
        super(name, cards);
    }

    @Override
    public boolean isReceivable() {
        return cards.calculate() < RECEIVE_SIZE;
    }
}
