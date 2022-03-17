package blackjack.domain.player;

import blackjack.domain.card.PlayingCards;

public class Dealer extends AbstractPlayer implements Player {

    public static final int HIT_MAX_POINT = 16;
    private static final String NAME = "딜러";

    public Dealer(String name, PlayingCards playingCards) {
        super(name, playingCards);
    }

    public Dealer() {
        this(NAME, new PlayingCards());
    }

    @Override
    public boolean isCanHit() {
        return playingCards.calculatePoints() < HIT_MAX_POINT;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public boolean isWin(Player player) {
        if (player.isDealer()) {
            return player.isWin(this);
        }
        if (player.isBlackJack() && !isBlackJack()) {
            return false;
        }
        if (player.isBust() && !this.isBust()) {
            return true;
        }
        return (player.isLose(this) && (!this.isBust()));
    }
}
