package participant;

public class Wallet {

    private double earnedMoney;
    private final int bettingMoney;

    private Wallet(double earnedMoney, int bettingMoney) {
        this.earnedMoney = earnedMoney;
        this.bettingMoney = bettingMoney;
    }

    public static Wallet of(int bettingMoney) {
        return new Wallet(0, bettingMoney);
    }

    public void updateMoney(double rate) {
        earnedMoney = bettingMoney + (bettingMoney * rate);
    }

    public double calculateProfit() {
        return earnedMoney - bettingMoney;
    }

    public double getEarnedMoney() {
        return earnedMoney;
    }
}
