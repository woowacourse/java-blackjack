package domain.user;

public class UserInformation {
    private final UserName userName;
    private final BettingAmount bettingAmount;

    private UserInformation(UserName userName, BettingAmount bettingAmount) {
        this.userName = userName;
        this.bettingAmount = bettingAmount;
    }

    public static UserInformation from(UserName userName, int bettingAmount) {
        return new UserInformation((userName), new BettingAmount(bettingAmount));
    }

    public String getNameValue() {
        return this.userName.getValue();
    }


    public int getBettingAmountValue() {
        return this.bettingAmount.getValue();
    }
}
