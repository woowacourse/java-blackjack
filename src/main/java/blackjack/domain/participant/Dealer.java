package blackjack.domain.participant;

import blackjack.domain.Result;

public class Dealer extends Participant {

    private static final int LIMIT_SCORE = 17;

    public Dealer() {
    }

    public Result judge(final Player player) {
        if (isDealerWinThePlayer(player)) {
            return Result.LOSE;
        }
        if (isDealerLoseThePlayer(player)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    private boolean isDealerWinThePlayer(final Player player) {
        return player.isBurst() || getScore() > player.getScore();
    }

    private boolean isDealerLoseThePlayer(final Player player) {
        return getScore() < player.getScore();
    }

    @Override
    public boolean isHitable() {
        return getScore() < LIMIT_SCORE;
    }
}
