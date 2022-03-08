package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Gamer implements Player {

    public static final int LIMIT_GAMER_TOTAL_POINT = 21;

    private final String name;
    private final Cards cards;

    public Gamer(final String name) {
        checkBannedName(name);
        this.name = name;
        cards = new Cards();
    }

    private void checkBannedName(final String name) {
        if (name.equals(Dealer.DEALER_NAME)) {
            throw new IllegalArgumentException("[ERROR] Gamer의 이름은 딜러일 수 없습니다.");
        }
    }

    @Override
    public void receiveCard(final Card card) {
        cards.save(card);
    }

    @Override
    public List<Card> openCards() {
        return new ArrayList<>(cards.getCards().subList(0, 2));
    }

    @Override
    public List<Card> showCards() {
        return List.copyOf(cards.getCards());
    }

    @Override
    public int calculateResult() {
        return cards.calculateTotalPoint();
    }

    @Override
    public boolean isReceivable() {
        return calculateResult() <= LIMIT_GAMER_TOTAL_POINT;
    }

    public String getName() {
        return name;
    }

}
