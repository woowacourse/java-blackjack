package domain.participant;

import domain.BetMoney;
import domain.Score;
import domain.card.Hand;

public class Player extends Participant {
    private final Name name;
    private BetMoney betMoney;

    public Player(String name) {
        this.name = new Name(name);
    }

    public Player(Player player) {
        super(new Hand(player.hand));
        this.name = player.name;
    }

    public Player(String name, int betMoney) {
        super();
        this.name = new Name(name);
        this.betMoney = BetMoney.of(betMoney);
    }

    public BetMoney getResult(Participant target) {
        if (isBlackjack() && target.isBlackjack()) {
            return betMoney.draw();
        }
        if (isBlackjack()) {
            return betMoney.blackjack();
        }
        if (target.isBlackjack()) {
            return betMoney.lose();
        }
        if (isBust()) {
            return betMoney.lose();
        }
        if (target.isBust()) {
            return betMoney.win();
        }
        return judgeByScore(target);
    }

    private BetMoney judgeByScore(Participant target) {
        Score score = getTotalSum();
        Score targetScore = target.getTotalSum();

        if (score.isEqualTo(targetScore)) {
            return betMoney.draw();
        }
        if (score.isGreaterThan(targetScore)) {
            return betMoney.win();
        }
        return betMoney.lose();
    }

    public void setBetMoney(long value) {
        this.betMoney = BetMoney.of(value);
    }

    public Name getName() {
        return name;
    }
}
