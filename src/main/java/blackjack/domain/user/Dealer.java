package blackjack.domain.user;

import blackjack.domain.GameRule;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;

import java.util.Objects;

public class Dealer extends User implements GameRule {
    private static final String NULL_ERR_MSG = "%s이(가) 없습니다.";
    private static final String name = "딜러";
    public static final int LOWER_BOUND = 16;

    public Dealer() {
        super(name);
    }

    public Card getFirstCard() {
        return super.getCards().get(0);
    }

    @Override
    public void drawCard(CardDeck deck) {
        Objects.requireNonNull(deck, String.format(NULL_ERR_MSG, "카드"));
        int receivableCardSize = receivableCardSize();
        for (int i = 0; i < receivableCardSize; i++) {
            Card card = deck.getCard();
            super.getCards().add(card);
        }
    }

    @Override
    public boolean receivable() {
        super.getPoint().computePoint(super.getCards());
        if (super.getPoint().compareTo(new Point(LOWER_BOUND)) == -1) {
            return true;
        }
        return false;
    }

    @Override
    public int receivableCardSize() {
        if (super.getCards().size() == 0) {
            return 2;
        }
        if (receivable()) {
            return  1;
        }
        return 0;
    }
}
