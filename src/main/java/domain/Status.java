package domain;

public class Status {

    private final Name name;
    private final Account account;

    public Status(final Name name, final Account account) {
        this.name = name;
        this.account = account;
    }

    public String getName() {
        return name.name();
    }

    public int getAccount() {
        return account.account();
    }
}
