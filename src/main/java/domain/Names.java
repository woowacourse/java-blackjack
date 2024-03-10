package domain;

import java.util.List;

public class Names {

    private final List<Name> names;

    public Names(List<Name> names) {
        validate(names);
        this.names = names;
    }

    private void validate(List<Name> names) {
        if (names.size() != names.stream().distinct().count()) {
            throw new IllegalArgumentException("이름은 중복일 수 없습니다");
        }
    }

    public List<Name> getNames() {
        return names;
    }
}
