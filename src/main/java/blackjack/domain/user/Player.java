package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;

import java.util.Objects;

public class Player extends User {
    private static final String NULL_ERR_MSG = "%s이(가) 없습니다.";
    public Player(String name) {
        super(name);
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
        return !(new Point(getCards()).isBust());
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
