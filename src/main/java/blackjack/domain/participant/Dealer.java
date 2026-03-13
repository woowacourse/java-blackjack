package blackjack.domain.participant;

import blackjack.domain.result.GameResult;
import blackjack.domain.card.Hand;
import blackjack.domain.result.Score;

public class Dealer extends Participant {
    private static final int HIT_THRESHOLD = 16;

    public Dealer(Name NAme, Hand hand) {
        super(NAme, hand);
    }

    public Dealer(String name, Hand hand) {
        super(new Name(name), hand);
    }

    @Override
    public boolean canHit() {
        return !getScore().isBiggerThan(HIT_THRESHOLD);
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
        Score dealerScore = this.getScore();
        Score playerScore = player.getScore();

        if (dealerScore.isBiggerThan(playerScore)) {
            return GameResult.DEALER_WIN;
        }
        if (playerScore.isBiggerThan(dealerScore)) {
            return GameResult.PLAYER_WIN;
        }
        return GameResult.PUSH;
    }
}
