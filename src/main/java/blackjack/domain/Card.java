package blackjack.domain;

import java.util.Objects;

public class Card {
    private static final String CARD_GENERATE_LOCK_EXCEPTION
            = "[ERROR] 필요한 모든 카드가 생성되었습니다. 이제 카드를 새로 생성할 수 없습니다.";

    private static boolean lock = false;

    private final String name;
    private final int number;

    public Card(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public static Card generateCard(String name, int number) {
        if (lock) {
            throw new RuntimeException(CARD_GENERATE_LOCK_EXCEPTION);
        }
        return new Card(name, number);
    }

    public static void lock() {
        lock = true;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return Objects.equals(name, card.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
