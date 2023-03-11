package domain;

import java.util.Objects;

public class Account {

    private int account;

    public Account(final int account) {
        this.account = account;
    }

    public void bust(final int loseMoney) {
        account = loseMoney * -1;
    }

    public void addAccount(final int winningMoney) {
        this.account += winningMoney;
    }

    public void subAccount(final int losingMoney) {
        account -= losingMoney;
    }

    public int getAccount() {
        return account;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account1 = (Account) o;
        return account == account1.account;
    }

    @Override
    public int hashCode() {
        return Objects.hash(account);
    }
}
