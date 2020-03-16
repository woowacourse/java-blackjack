package domain.user;

import utils.StringUtils;

public class Name {
    private String name;

    Name(String name) {
        StringUtils.checkNameNullAndEmpty(name);
        this.name = name;
    }

    public boolean isSame(String name) {
        return this.name.equals(name);
    }

    String getName() {
        return name;
    }
}
