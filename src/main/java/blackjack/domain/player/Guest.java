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
    public boolean isWin(Player player) {
        if (!player.isDealer()) {
            return player.isWin(this);
        }
        if (player.isBlackJack() && !isBlackJack()) {
            return false;
        }
        if (player.isBust() && !this.isBust()) {
            return true;
        }
        return (player.isLose(playingCards.sumPoints())) && (!this.isBust());
    }
}
