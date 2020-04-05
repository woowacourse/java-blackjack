package domain.gamer;

import domain.card.Card;
import domain.result.Result;
import domain.result.score.Score;
import domain.result.score.ScoreCalculable;

import java.util.List;

public class Player extends Gamer {
    private static final Money DEFAULT_MONEY = new Money(0);

    private Money bettingMoney;

    public Player(Name name) {
        this(name, new Hand(), DEFAULT_MONEY);
    }

    public Player(Name name, Money bettingMoney) {
        this(name, new Hand(), bettingMoney);
    }

    public Player(Name name, Hand hand) {
        this(name, hand, DEFAULT_MONEY);
    }

    public Player(Name name, Hand hand, Money bettingMoney) {
        super(name, hand);
        this.bettingMoney = bettingMoney;
    }

    @Override
    public List<Card> openInitialCards() {
        return super.openAllCards();
    }

    @Override
    public boolean canDrawMore(ScoreCalculable scoreCalculable) {
        return !isBurst(scoreCalculable);
    }

    public Result determineResult(Dealer dealer, ScoreCalculable scoreCalculable) {
        if (this.isBlackJack(scoreCalculable) || dealer.isBlackJack(scoreCalculable)) {
            return deriveBlackJackExistCase(dealer, scoreCalculable);
        }

        if (this.isBurst(scoreCalculable) || dealer.isBurst(scoreCalculable)) {
            return deriveBurstExistCase(scoreCalculable);
        }

        return deriveUsualCase(dealer, scoreCalculable);
    }

    private Result deriveBlackJackExistCase(Dealer dealer, ScoreCalculable scoreCalculable) {
        if (this.isBlackJack(scoreCalculable) && dealer.isBlackJack(scoreCalculable)) {
            return new Result(name, DRAW_PROFIT);
        }

        if (this.isBlackJack(scoreCalculable)) {
            return new Result(name, bettingMoney.multiply(BLACKJACK_BONUS));
        }

        return new Result(name, bettingMoney.negate());
    }

    private Result deriveBurstExistCase(ScoreCalculable scoreCalculable) {
        if (this.isBurst(scoreCalculable)) {
            return new Result(name, bettingMoney.negate());
        }

        return new Result(name, bettingMoney);
    }

    private Result deriveUsualCase(Dealer dealer, ScoreCalculable scoreCalculable) {
        Score playerScore = this.calculateScore(scoreCalculable);
        Score dealerScore = dealer.calculateScore(scoreCalculable);

        if (playerScore.isBiggerThan(dealerScore)) {
            return new Result(name, bettingMoney);
        }

        if (playerScore.equals(dealerScore)) {
            return new Result(name, DRAW_PROFIT);
        }

        return new Result(name, bettingMoney.negate());
    }
}
