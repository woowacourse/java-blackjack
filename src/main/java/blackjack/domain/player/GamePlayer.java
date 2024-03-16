package blackjack.domain.player;


import blackjack.domain.card.Cards;

public class GamePlayer extends Participant {
    public GamePlayer(final Name name, final Cards cards) {
        super(name, cards);
    }

    public GamePlayer(final PlayerInfo playerInfo) {
        super(playerInfo);
    }

    @Override
    public boolean isReceivable() {
        return this.state.isHit();
    }
}
