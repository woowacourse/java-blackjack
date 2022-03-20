package blackjack.domain.state;

import blackjack.domain.card.PlayingCards;
import blackjack.domain.player.Player;
import blackjack.domain.result.Match;

public class Blackjack extends Finished {

    public Blackjack(final PlayingCards playingCards) {
        super(playingCards);
    }

    @Override
    public Match matchResult(Player player) {
        if (player.isBlackjack()) {
            return Match.DRAW;
        }
        return Match.WIN_BLACKJACK;
    }
}
