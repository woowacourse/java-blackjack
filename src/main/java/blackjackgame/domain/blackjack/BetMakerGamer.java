package blackjackgame.domain.blackjack;

public class BetMakerGamer {
    private final String name;
    private final BetMoney betMoney;

    public BetMakerGamer(String name, BetMoney betMoney) {
        this.name = name;
        this.betMoney = betMoney;
    }
}
