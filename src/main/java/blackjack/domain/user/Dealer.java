package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Dealer extends User {

    public Dealer(Cards cards) {
        super("딜러", cards);
    }

    public Card getInitCard() {
        return cards.getCards().iterator().next();
    }

    @Override
    public boolean isHit() {
        return cards.canDealerDraw();
    }
}
