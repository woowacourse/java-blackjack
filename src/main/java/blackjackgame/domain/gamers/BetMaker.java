package blackjackgame.domain.gamers;

public class BetMaker {
    private final String name;
    private final Double betMoney;

    public BetMaker(String name, Double betMoney) {
        this.name = name;
        this.betMoney = betMoney;
    }

    public Double getBetMoney() {
        return betMoney;
    }
}
