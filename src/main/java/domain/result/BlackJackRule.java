package domain.result;

import domain.gamer.*;
import domain.result.score.Score;
import domain.result.score.ScoreCalculable;

import java.util.List;

public class BlackJackRule implements ScoreCalculable, ResultDerivable {
    private static final Score BLACKJACK_SCORE = Score.of(21);
    private static final Score DEALER_THRESHOLD_SCORE = Score.of(16);
    private static final Score ACE_ADDITIONAL_SCORE = Score.of(10);
    private static final int DEALER_MAXIMUM_CARDS_AMOUNT = 3;

    @Override
    public Score calculateScore(AbstractGamer gamer) {
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
    public boolean checkCanDrawMore(AbstractGamer gamer) {
        if (gamer.getClass() == Player.class) {
            return !isBurst(gamer);
        }

        return !calculateScore(gamer).isBiggerThan(DEALER_THRESHOLD_SCORE)
                && gamer.getHand().getCards().size() < DEALER_MAXIMUM_CARDS_AMOUNT;
    }

    @Override
    public boolean isBurst(AbstractGamer gamer) {
        return calculateScore(gamer).isBiggerThan(BLACKJACK_SCORE);
    }

    @Override
    public Result derivePlayerResult(Player player, Dealer dealer) {
        Score playerScore = calculateScore(player);
        Score dealerScore = calculateScore(dealer);
        Money bettingMoney = player.getBettingMoney();

        if (playerScore.isBiggerThan(BLACKJACK_SCORE)) {
            return new Result(player.getName(), bettingMoney.reverse());
        }

        if (dealerScore.isBiggerThan(BLACKJACK_SCORE)) {
            return new Result(player.getName(), bettingMoney);
        }

        if (playerScore.equals(dealerScore)) {
            return new Result(player.getName(), new Money(0));
        }

        if (playerScore.equals(BLACKJACK_SCORE)) {
            return new Result(player.getName(), bettingMoney.multiply(1.5));
        }

        if (dealerScore.equals(BLACKJACK_SCORE)) {
            return new Result(player.getName(), bettingMoney.reverse().multiply(1.5));
        }

        if (playerScore.isBiggerThan(dealerScore)) {
            return new Result(player.getName(), bettingMoney);
        }

        return new Result(player.getName(), bettingMoney.reverse());
    }

    @Override
    public Result deriveDealerResult(List<Result> playerResults) {
        Money totalSum = playerResults.stream()
                .map(Result::getBettingMoney)
                .reduce(new Money(0), (a, b) -> a.plus(b));

        return new Result(new Name("딜러"), totalSum.reverse());
    }
}
