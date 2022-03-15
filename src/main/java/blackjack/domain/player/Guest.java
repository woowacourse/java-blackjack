package blackjack.domain.player;

import blackjack.domain.card.Deck;
import blackjack.domain.result.Match;

public class Guest extends AbstractPlayer implements Player {

    public static final int MAX_POINT = 21;

    public Guest(String name) {
        this.playingCards = new Deck();
        this.name = name;
    }

    @Override
    public boolean isOverMoreCardLimit() {
        return playingCards.sumPoints() > MAX_POINT;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public boolean isWin(Player guest, Player dealer) {
        int points = playingCards.sumPoints();
        if (dealer.isLose(points) && points <= Match.MAX_WINNER_POINT) {
            return true;
        }
        if (dealer.isOverPointLimit() && points <= Match.MAX_WINNER_POINT) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isDraw(Player dealer) {
        return playingCards.sumPoints() == dealer.getDeck().sumPoints();
    }
}
