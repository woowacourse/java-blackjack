package domain.user;

public class UserInformation {
    private final UserName userName;
    private final BettingAmount bettingAmount;

    public UserInformation(UserName userName, BettingAmount bettingAmount) {
        this.userName = userName;
        this.bettingAmount = bettingAmount;
    }

    public static UserInformation of(UserName userName, int bettingAmountInput) {
        return new UserInformation(userName, new BettingAmount(bettingAmountInput));
    }

    public String getNameValue() {
        return this.userName.getValue();
    }
}
