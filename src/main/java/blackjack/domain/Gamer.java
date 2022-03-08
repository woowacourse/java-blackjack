package blackjack.domain;

import java.util.List;

public class Gamer implements Player{

    private final static String BANNED_GAMER_NAME = "딜러";

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

    @Override
    public void receiveCard(Card card) {
        cards.save(card);
    }

    @Override
    public List<Card> openCards() {
        return null;
    }

    @Override
    public List<Card> showCards() {
        return cards.getCards();
    }

    @Override
    public int calculateResult() {
        return 0;
    }

    @Override
    public boolean isSatisfyReceiveCardCondition() {
        return false;
    }

    public String getName() {
        return name;
    }

}
