package blackjack.money;

public record PlayerBet(String name, BetMoney betMoney) {

    public int betMoneyAmount() {
        return betMoney.getAmount();
    }
}
