package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Dealer {
    private static final String NAME = "딜러";
    private static final int BOUND = 16;

    private final Player player;

    public Dealer() {
        this.player = new Player(NAME);
    }

    public void addCard(Card card) {
        player.addCard(card);
    }

    public String getName() {
        return NAME;
    }

    public Map<String, List<String>> getInfo() {
        Map<String, List<String>> info = new LinkedHashMap<>();
        info.put(getName(), getCards());
        return info;
    }

    public Card getCard(int index) {
        return player.getCard(index);
    }

    public List<String> getCards() {
        return player.getCards();
    }

    public boolean isOverStandard() {
        return player.getCardsSum() > BOUND;
    }

    public int getCardsSum() {
        return player.getCardsSum();
    }
}
