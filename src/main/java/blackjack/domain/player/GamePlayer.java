package blackjack.domain.player;


import blackjack.domain.common.Name;
import blackjack.domain.card.Cards;

public class GamePlayer extends Player implements CardReceivable {
    private static final Integer RECEIVE_SIZE = 21;

    public GamePlayer(final Name name, final Cards cards) {
        super(name, cards);
    }

    @Override
    public boolean isReceivable() {
        return cards.sum() < RECEIVE_SIZE;
    }
}
