package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String name;
    private final List<Card> cards;

    public User(String name) {
        validate(name);
        this.name = name;
        this.cards = new ArrayList<>();
    }

    private void validate(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("유저 이름은 5자가 넘을 수 없습니다.");
        }
    }

}
