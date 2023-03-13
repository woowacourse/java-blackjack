package domain.user;

public class UserInformation {
    private final UserName userName;
    private final BettingMoney bettingMoney;

    public UserInformation(UserName userName, BettingMoney bettingMoney) {
        this.userName = userName;
        this.bettingMoney = bettingMoney;
    }

    public String getNameValue() {
        return this.userName.getName();
    }


    public int getBettingMoneyValue() {
        return this.bettingMoney.getMoney();
    }
}
