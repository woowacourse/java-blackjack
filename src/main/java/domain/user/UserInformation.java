package domain.user;

public class UserInformation {
    private final UserName userName;
    private final BettingAmount bettingAmount;

    public UserInformation(UserName userName, BettingAmount bettingAmount) {
        this.userName = userName;
        this.bettingAmount = bettingAmount;
    }

    public String getNameValue() {
        return this.userName.getValue();
    }


    public int getBettingAmountValue() {
        return this.bettingAmount.getValue();
    }
}
