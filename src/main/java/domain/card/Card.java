package domain.card;

import static domain.card.CardNumber.ACE;

public record Card(CardShape shape, CardNumber number) {
    public int value() {
        return number.value();
    }

    public boolean isAce() {
        return number == ACE;
    }
}
