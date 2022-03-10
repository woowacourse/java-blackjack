package blackjack.domain.player;

import blackjack.domain.Answer;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Gamer implements Player {

    public static final int LIMIT_GAMER_TOTAL_POINT = 21;

    private static final int GAMER_OPEN_CARD_NUMBER = 2;

    private final String name;
    private final Cards cards;

    public Gamer(final String name) {
        checkEmptyName(name);
        checkBannedName(name);
        this.name = name;
        cards = new Cards();
    }

    private void checkEmptyName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] Gamer의 이름은 공백일 수 없습니다.");
        }
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
        return new ArrayList<>(cards.getCards().subList(0, GAMER_OPEN_CARD_NUMBER));
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

    public boolean isHit(final Answer answer) {
        return answer == Answer.YES;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Gamer gamer = (Gamer) o;
        return Objects.equals(name, gamer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
