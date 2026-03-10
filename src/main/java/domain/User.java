package domain;

import vo.Name;

public class User extends Participant {
    private final Name name;

    public User(String name) {
        this.name = new Name(name);
    }

    public String getName() {
        return name.getName();
    }
}