package blackjack.domain.state;

import blackjack.domain.card.PlayingCards;
import blackjack.domain.player.Player;
import blackjack.domain.result.Match;

public class Stay extends Finished {

    Stay(final PlayingCards playingCards) {
        super(playingCards);
    }

    @Override
    public Match matchResult(Player player) {
        if (player.isBlackjack()) {
            return Match.LOSE_BLACKJACK;
        }
        if (player.isDraw(playingCards())) {
            return Match.DRAW;
        }
        if (player.isLose(playingCards()) || player.isBust()) {
            return Match.WIN;
        }
        return Match.LOSE;
    }
}
