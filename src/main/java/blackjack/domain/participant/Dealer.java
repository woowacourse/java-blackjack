package blackjack.domain.participant;

import blackjack.domain.GameResult;

public class Dealer extends Participant {

    private static final int LIMIT_SCORE = 17;

    public Dealer() {
    }

    public GameResult judge(final Player player) {
        if (isDealerWinThePlayer(player)) {
            return GameResult.LOSE;
        }
        if (isDealerLoseThePlayer(player)) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    private boolean isDealerWinThePlayer(Player player) {
        return player.isBurst() || getScore() > player.getScore();
    }

    private boolean isDealerLoseThePlayer(Player player) {
        return getScore() < player.getScore();
    }

    @Override
    public boolean isHitable() {
        return getScore() < LIMIT_SCORE;
    }
}
