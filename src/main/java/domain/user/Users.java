package domain.user;

import java.util.Iterator;
import java.util.List;

public class Users implements Iterable<User> {
    private final List<User> users;

    public Users(final List<User> users) {
        this.users = users;
    }

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }
}
