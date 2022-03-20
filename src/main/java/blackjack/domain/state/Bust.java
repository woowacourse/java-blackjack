package blackjack.domain.state;

import blackjack.domain.card.PlayingCards;
import blackjack.domain.player.Player;
import blackjack.domain.result.Match;

public class Bust extends Finished {

    protected Bust(PlayingCards playingCards) {
        super(playingCards);
    }

    @Override
    public Match matchResult(Player player) {
        if (player.isBust()) {
            return Match.DRAW;
        }
        if (player.isBlackjack()) {
            return Match.LOSE_BLACKJACK;
        }
        return Match.LOSE;
    }
}
