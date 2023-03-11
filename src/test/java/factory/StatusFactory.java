package factory;

import domain.Account;
import domain.Name;
import domain.Status;

public class StatusFactory {

    public static Status createStatus(final String name, final int account) {
        return new Status(new Name(name), new Account(account));
    }
}
