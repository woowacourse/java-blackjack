package blackjack.domain.player;

import blackjack.domain.card.Deck;
import blackjack.domain.result.Match;

public class Dealer extends AbstractPlayer implements Player {

    public static final String NAME = "딜러";
    public static final int MAX_POINT = 16;
    public static final int EXCEED_POINT = 17;

    public Dealer() {
        this.playingCards = new Deck();
        this.name = NAME;
    }

    @Override
    public boolean isOverMoreCardLimit() {
        return playingCards.sumPoints() > MAX_POINT;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public boolean isWin(Player guest, Player dealer) {
        int points = playingCards.sumPoints();
        if (guest.isLose(points) && points <= Match.MAX_WINNER_POINT) {
            return true;
        }
        if (guest.isOverMoreCardLimit() && points <= Match.MAX_WINNER_POINT) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isDraw(Player guest) {
        return playingCards.sumPoints() == guest.getDeck().sumPoints();
    }
}
