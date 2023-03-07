package domain;

public class Account {

    private static final int MIN_BETTING_ACCOUNT = 1000;

    private final int account;

    public Account(final int account) {
        validate(account);
        this.account = account;
    }

    private void validate(final int account) {
        if(account < MIN_BETTING_ACCOUNT) {
            throw new IllegalArgumentException(Message.BETTING_MONEY_NEED_MORE.getMessage());
        }
    }

    public int account() {
        return account;
    }
}
