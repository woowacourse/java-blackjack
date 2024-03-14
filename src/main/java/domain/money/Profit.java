package domain.money;

public class Profit {
    private Money money;

    public Profit() {
        money = new Money();
    }

    // TODO: Profit은 Money가 계속 바뀔 수 있으니까  VO가 아니겠지?
    public void increase(final Money receivedMoney) {
        money = money.add(receivedMoney);
    }

    public int getValue() {
        return money.getValue();
    }
}
