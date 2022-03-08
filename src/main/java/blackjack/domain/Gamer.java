package blackjack.domain;

import java.util.List;

public class Gamer {

    private static final String BANNED_GAMER_NAME = "딜러";

    private final String name;
    private final Cards cards;

    public Gamer(final String name) {
        checkBannedName(name);
        this.name = name;
        cards = new Cards();
    }

    private void checkBannedName(String name) {
        if (name.equals(BANNED_GAMER_NAME)){
            throw new IllegalArgumentException("[ERROR] Gamer의 이름은 딜러일 수 없습니다.");
        }
    }

    public void receiveCard(Card card) {
        cards.save(card);
    }

    public String getName() {
        return name;
    }

    public List<Card> cards() {
        return cards.getCards();
    }
}
