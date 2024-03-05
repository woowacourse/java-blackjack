package domain;

import java.util.ArrayList;
import java.util.List;

public class Packet { //TODO: 더 좋은 이름이 있다면 날 설득해줘

    private final List<Card> cards;

    private Packet(final List<Card> cards) {
        this.cards = cards;
    }

    public static Packet createEmptyPacket() {
        return new Packet(new ArrayList<>());
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }
}
