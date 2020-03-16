package domain.user;

import utils.StringUtils;

public class Player extends User {
    private Name name;

    public Player(String name) {
        StringUtils.checkNameNullAndEmpty(name);
        this.name = new Name(name);
    }

    public String getName() {
        return name.getName();
    }
}
