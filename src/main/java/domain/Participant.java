package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Participant {
    private final Name name;
    private final Cards cards;

    public Participant(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Map<String, List<String>> getInfo() {
        Map<String, List<String>> info = new LinkedHashMap<>();
        info.put(getName().getName(), getCards());
        return info;
    }

    public Name getName() {
        return name;
    }

    public Card getCard(int index) {
        return cards.getCard(index);
    }

    public List<String> getCards() {
        return cards.getCards();
    }

    public int getCardsSum() {
        return cards.getSum();
    }
}
