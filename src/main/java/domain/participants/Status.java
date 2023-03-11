package domain.participants;

public class Status {

    private final Name name;
    private final Account account;

    public Status(final Name name, final Account account) {
        this.name = name;
        this.account = account;
    }

    public void bustAccount(final int loseMoney) {
        account.bust(loseMoney);
    }

    public void winGame(final int price) {
        account.addAccount(price);
    }

    public void loseGame(final int loseMoney) {
        account.subAccount(loseMoney);
    }
    
    public String getName() {
        return name.name();
    }

    public int getAccount() {
        return account.getAccount();
    }
}
