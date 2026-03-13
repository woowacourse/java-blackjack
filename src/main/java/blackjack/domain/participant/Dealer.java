package blackjack.domain.participant;

import blackjack.domain.card.Hand;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.Score;

public class Dealer extends Participant {
    private static final Name DEALER_NAME = new Name("딜러");
    private static final int HIT_THRESHOLD = 16;

    public Dealer(Name name, Hand hand) {
        super(name, hand);
    }

    public Dealer(String name, Hand hand) {
        super(new Name(name), hand);
    }

    public static Dealer create() {
        return new Dealer(DEALER_NAME, new Hand());
    }

    @Override
    public boolean canHit() {
        return !getScore().isBiggerThan(HIT_THRESHOLD);
    }

    public static boolean isDealerName(Name name) {
        return DEALER_NAME.equals(name);
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
