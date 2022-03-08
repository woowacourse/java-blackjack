package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    Cards() {
        cards = new ArrayList<>();
    }

    public void addCard(final Card card) {
        checkCardNull(card);
        cards.add(card);
    }

    private void checkCardNull(final Card card) {
        if (card == null) {
            throw new IllegalArgumentException("[ERROR] 올바른 카드를 입력해주세요.");
        }
    }

    public List<Card> getCards() {
        return this.cards;
    }
}
