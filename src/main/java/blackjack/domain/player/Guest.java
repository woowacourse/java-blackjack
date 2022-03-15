package blackjack.domain.player;

import blackjack.domain.card.PlayingCards;

public class Guest extends AbstractPlayer implements Player {

    public Guest(String name, PlayingCards playingCards) {
        super(name, playingCards);
    }

    @Override
    public boolean isCanHit() {
        return playingCards.isHit();
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
        if (dealer.isBust() && !this.isBust()) {
            return true;
        }
        return (dealer.isLose(points)) && (!this.isBust());
    }
}
