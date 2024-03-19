package domain.result;

public class Money {

    private int value;

    public Money(int value) {
        this.value = value;
    }

    public void applyProfitRate(WinState winState) {
        value = (int) (value * winState.getProfitRate());
    }

    public int getMoney() {
        return value;
    }
}
