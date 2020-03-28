package domain.result;

import domain.gamer.*;
import domain.result.score.Score;

import java.util.List;

import static domain.gamer.Dealer.DEALER_NAME;

public class BlackJackRule extends GameRule {
    private static final Score BLACKJACK_SCORE = Score.of(21);
    private static final Score DEALER_THRESHOLD_SCORE = Score.of(16);
    private static final Score ACE_ADDITIONAL_SCORE = Score.of(10);
    private static final int DEALER_MAXIMUM_CARDS_AMOUNT = 3;
    private static final int BLACKJACK_CARDS_AMOUNT = 2;
    private static final Money DRAW_PROFIT = new Money(0);
    private static final double BLACKJACK_BONUS = 1.5;

    @Override
    public Score calculateScore(Gamer gamer) {
        Hand hand = gamer.getHand();
        Score defaultSum = hand.calculateDefaultSum();

        if (hand.hasAce()) {
            return updateAceScore(defaultSum);
        }

        return defaultSum;
    }

    private Score updateAceScore(Score defaultSum) {
        Score aceAdditionalScore = defaultSum.plus(ACE_ADDITIONAL_SCORE);

        if (aceAdditionalScore.isBiggerThan(BLACKJACK_SCORE)) {
            return defaultSum;
        }

        return aceAdditionalScore;
    }

    @Override
    public boolean canDrawMore(Gamer gamer) {
        if (gamer.getClass() == Player.class) {
            return !isBurst(gamer);
        }

        return !calculateScore(gamer).isBiggerThan(DEALER_THRESHOLD_SCORE)
                && gamer.getHand().size() < DEALER_MAXIMUM_CARDS_AMOUNT;
    }

    private boolean isBurst(Gamer gamer) {
        return calculateScore(gamer).isBiggerThan(BLACKJACK_SCORE);
    }

    @Override
    public Result derivePlayerResult(Player player, Dealer dealer) {
        if (isBlackJack(player) || isBlackJack(dealer)) {
            return deriveBlackJackExistCase(player, dealer);
        }

        if (isBurst(player) || isBurst(dealer)) {
            return deriveBurstExistCase(player, dealer);
        }

        return deriveUsualCase(player, dealer);
    }

    private boolean isBlackJack(Gamer gamer) {
        return calculateScore(gamer).equals(BLACKJACK_SCORE)
                && gamer.getHand().size() == BLACKJACK_CARDS_AMOUNT;
    }

    private Result deriveBlackJackExistCase(Player player, Dealer dealer) {
        if (isBlackJack(player) && isBlackJack(dealer)) {
            return new Result(player.getName(), DRAW_PROFIT);
        }

        if (isBlackJack(player)) {
            return new Result(player.getName(), player.getBettingMoney().multiply(BLACKJACK_BONUS));
        }

        return new Result(player.getName(), player.getBettingMoney().negate());
    }

    private Result deriveBurstExistCase(Player player, Dealer dealer) {
        if (isBurst(player)) {
            return new Result(player.getName(), player.getBettingMoney().negate());
        }

        return new Result(player.getName(), player.getBettingMoney());
    }

    private Result deriveUsualCase(Player player, Dealer dealer) {
        Score playerScore = calculateScore(player);
        Score dealerScore = calculateScore(dealer);

        if (playerScore.isBiggerThan(dealerScore)) {
            return new Result(player.getName(), player.getBettingMoney());
        }

        if (playerScore.equals(dealerScore)) {
            return new Result(player.getName(), DRAW_PROFIT);
        }

        return new Result(player.getName(), player.getBettingMoney().negate());
    }

    @Override
    public Result deriveDealerResult(List<Result> playerResults) {
        Money totalSum = playerResults.stream()
                .map(Result::getProfit)
                .reduce(new Money(0), (a, b) -> a.plus(b));

        return new Result(DEALER_NAME, totalSum.negate());
    }
}
