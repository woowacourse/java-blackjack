package blackjack.domain.player;

import blackjack.domain.card.PlayingCards;

public class Dealer extends AbstractPlayer implements Player {

    public static final String NAME = "딜러";
    public static final int HIT_MAX_POINT = 16;

    public Dealer(String name, PlayingCards playingCards) {
        super(name, playingCards);
    }

    public Dealer() {
        this(NAME, new PlayingCards());
    }

    @Override
    public boolean isCanHit() {
        return playingCards.sumPoints() < HIT_MAX_POINT;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public boolean isWin(Player guest) {
        int points = playingCards.sumPoints();
        if (guest.isBlackJack() && !isBlackJack()) {
            return false;
        }
        if (guest.isBust() && !this.isBust()) {
            return true;
        }
        return (guest.isLose(points)) && (!this.isBust());
    }
}
