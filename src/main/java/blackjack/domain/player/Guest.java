package blackjack.domain.player;

import blackjack.domain.card.PlayingCards;

public class Guest extends AbstractPlayer implements Player {

    public Guest(String name) {
        this.playingCards = new PlayingCards();
        this.name = name;
    }

    @Override
    public boolean isCanHit() {
        return playingCards.sumPoints() < PlayingCards.BLACKJACK_POINT;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public boolean isWin(Player dealer) {
        int points = playingCards.sumPoints();
        if (dealer.isBlackJack() && !isBlackJack()) {
            return false;
        }
        if (dealer.isBust() && points <= PlayingCards.BLACKJACK_POINT) {
            return true;
        }
        return (dealer.isLose(points)) && (points <= PlayingCards.BLACKJACK_POINT);
    }
}
