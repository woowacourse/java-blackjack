package factory;

import domain.participants.Account;
import domain.participants.Name;
import domain.participants.Status;

public class StatusFactory {

    public static Status createStatus(final String name, final int account) {
        return new Status(new Name(name), new Account(account));
    }
}
