package domain.user;

import utils.StringUtils;

public class Name {
    private String name;

    public Name(String name) {
        StringUtils.checkNameNullAndEmpty(name);
        this.name = name;
    }

    public boolean isSame(String name) {
        return this.name.equals(name);
    }

    public String getName() {
        return name;
    }
}
