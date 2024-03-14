package blackjackgame.domain.gamers;

public class BetMaker {
    private final Double betMoney;

    public BetMaker(String name, Double betMoney) {
        this.betMoney = betMoney;
    }

    public Double getBetMoney() {
        return betMoney;
    }
}
