package blackjackgame.domain.blackjack;

public class BetMaker {
    private final String name;
    private final BetMoney betMoney;

    public BetMaker(String name, BetMoney betMoney) {
        this.name = name;
        this.betMoney = betMoney;
    }
}
