package domain.gamer;

import domain.rule.BlackjackMatchResult;

public class Dealer extends Gamer {

    private static final Nickname NAME = new Nickname("딜러");
    private static final int HIT_THRESHOLD = 16;

    public Dealer() {
        super(NAME);
    }

    public BlackjackMatchResult determineMatchResultAgainst(Player player) {
        return getHand().determineMatchResultAgainst(player.getHand());
    }

    @Override
    public boolean canHit() {
        return getHand().calculateTotalPoint() <= HIT_THRESHOLD && !isBurst();
    }

}
