package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private static final int INITIAL_CARD_NUMBER = 2;
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

    public void initialDistribution(CardDeck cardDeck) {
        for (int i = 0; i < INITIAL_CARD_NUMBER; i++) {
            cards.add(cardDeck.draw());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User)o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
