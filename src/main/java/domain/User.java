package domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private List<Card> cards = new ArrayList<>();

    public User(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("이름은 blank값이 될 수 없습니다.");
        }
    }

    public String getName() {
        return this.name;
    }
}
