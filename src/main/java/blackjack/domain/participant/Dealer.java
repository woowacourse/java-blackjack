package blackjack.domain.participant;

import blackjack.domain.result.GameResult;
import blackjack.domain.card.Hand;
import blackjack.domain.result.Score;

public class Dealer extends Participant {
    private static final int HIT_THRESHOLD = 16;

    public Dealer(Name name, Hand hand) {
        super(name, hand);
    }

    public Dealer(String name, Hand hand) {
        super(new Name(name), hand);
    }

    @Override
    public boolean canHit() {
        return getScore().value() <= HIT_THRESHOLD;
    }

    public GameResult judgeAgainst(Player player) {
        if (player.isBust()) {
            return GameResult.DEALER_WIN;
        }
        if (this.isBust()) {
            return GameResult.PLAYER_WIN;
        }
        return competeScoreWith(player);
    }

    private GameResult competeScoreWith(Player player) {
        Score playerScore = player.getScore();
        int compare = this.getScore().compareTo(playerScore);

        if (compare > 0) {
            return GameResult.DEALER_WIN;
        }
        if (compare == 0) {
            return GameResult.PUSH;
        }
        return GameResult.PLAYER_WIN;
    }
}
