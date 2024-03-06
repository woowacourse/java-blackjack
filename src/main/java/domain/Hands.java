package domain;

import java.util.ArrayList;
import java.util.List;

public class Hands { //TODO: 더 좋은 이름이 있다면 날 설득해줘

    private static final int BLACK_JACK = 21;

    private final List<Card> cards;

    public Hands(final List<Card> cards) { //TODO validation
        this.cards = cards;
    }

    public static Hands createEmptyPacket() {
        return new Hands(new ArrayList<>());
    }

    public int sum() {
        int total = cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();

        if (hasAce() && total + 10 <= BLACK_JACK) { //TODO 메서드분리
            return total + 10;
        }

        return total;
    }

    public void add(final Card card) {
        cards.add(card);
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public int size() {
        return cards.size();
    }
}
