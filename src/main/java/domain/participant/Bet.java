package domain.participant;

public class Bet {
    private static final int MIN_BET_AMOUNT = 1;
    private static final int MAX_BET_AMOUNT = 100_000_000;
    private static final String INVALID_BET_AMOUNT = "배팅 금액은 %d ~ %d만 가능합니다.";
    private final Money money;

    public Bet(int amount) {
        validateBetAmount(amount);
        this.money = new Money(amount);
    }

    public int calculateBenefit(double benefitRate) {
        Money initMoney = money;
        Money resultMoney = money.multiply(benefitRate);
        return resultMoney.amount() - initMoney.amount();
    }

    private void validateBetAmount(int amount) {
        if (MIN_BET_AMOUNT > amount || amount > MAX_BET_AMOUNT) {
            throw new IllegalArgumentException(String.format(INVALID_BET_AMOUNT, MIN_BET_AMOUNT, MAX_BET_AMOUNT));
        }
    }
}
