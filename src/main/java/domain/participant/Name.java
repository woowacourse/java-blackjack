package domain.participant;

import view.Error;

public class Name {
    private final String name;

    public Name(final String name) {
        isEmptyName(name);
        this.name = name;
    }

    private void isEmptyName(final String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(Error.formatMessage("이름은 공백이 불가합니다."));
        }
    }

    public String getName() {
        return name;
    }
}
