package blackjack.model.participants;

import blackjack.model.cards.Cards;
import blackjack.model.results.Result;
import blackjack.vo.Money;
import blackjack.vo.Name;

public class Player extends Participant {
    private final Name name;
    private Money betAmount = new Money();

    public Player(String name) {
        this.name = new Name(name);
    }

    @Override
    public boolean canHit() {
        return !cards.isBust();
    }

    public Result getResult(Cards otherCards) {
        if (cards.isBust()) {
            return Result.LOSE;
        }
        if (cards.isBlackJack() && !otherCards.isBlackJack()) {
            return Result.WIN_BY_BLACKJACK;
        }
        if (otherCards.isBust()) {
            return Result.WIN;
        }
        return compareScore(otherCards);
    }

    public void betMoney(int betMoney) {
        betAmount = new Money(betMoney);
    }

    private Result compareScore(Cards otherCards) {
        int calculatedScore = cards.getScore();
        int otherScore = otherCards.getScore();

        if (calculatedScore > otherScore) {
            return Result.WIN;
        }
        if (calculatedScore < otherScore) {
            return Result.LOSE;
        }
        return Result.PUSH;
    }

    public String getName() {
        return name.value();
    }

    public Money getBetAmount() {
        return betAmount;
    }
}
