package blackjack.domain;

import java.util.Set;

public class Cards {
    private final Set<Card> value;

    public Cards(Set<Card> value) {
        this.value = value;
    }

    public int getSize() {
        return value.size();
    }
}
