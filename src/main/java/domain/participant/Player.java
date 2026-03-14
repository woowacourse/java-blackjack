package domain.participant;

import domain.BetMoney;
import domain.Score;
import domain.card.Hand;

public class Player extends Participant {
    private final Name name;
    private final BetMoney betMoney;

    private Player(Name name, BetMoney betMoney) {
        this.name = name;
        this.betMoney = betMoney;
    }

    private Player(Name name, BetMoney betMoney, Hand hand) {
        super(hand);
        this.name = name;
        this.betMoney = betMoney;
    }

    public static Player of(String name, String betMoney) {
        return new Player(Name.valueOf(name), BetMoney.valueOf(betMoney));
    }

    public static Player copyOf(Player player) {
        return new Player(player.name, player.betMoney, player.getCards());
    }

    public BetMoney judgeResult(Participant target) {
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

    public Name getName() {
        return name;
    }
}
