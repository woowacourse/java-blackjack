package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;

import java.util.Objects;

public class Dealer extends User {
    private static final String NULL_ERR_MSG = "%s이(가) 없습니다.";
    private static final String name = "딜러";
    public static final int LOWER_BOUND = 16;

    public Dealer() {
        super(name);
    }

    public Card getFirstCard() {
        return getCards().get(0);
    }

    @Override
    public void drawCard(CardDeck deck) {
        Objects.requireNonNull(deck, String.format(NULL_ERR_MSG, "카드"));
        int receivableCardSize = getReceivableCardSize();
        for (int i = 0; i < receivableCardSize; i++) {
            Card card = deck.getCard();
            getCards().add(card);
        }
    }

    @Override
    public boolean receivable() {
        Point point = new Point(getCards());
        if (point.compareTo(new Point(LOWER_BOUND)) == -1) {
            return true;
        }
        return false;
    }

    @Override
    public int getReceivableCardSize() {
        if (getCards().size() == 0) {
            return INITIAL_CARD_SIZE;
        }
        if (receivable()) {
            return ADDITIONAL_CARD_SIZE;
        }
        return 0;
    }
}
