package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.common.Name;
import blackjack.domain.result.ResultStatus;
import java.util.List;

public class Dealer extends Player {
    public static final Integer RECEIVE_SIZE = 16;
    private static final String DEFAULT_DEALER_NAME = "딜러";


    public Dealer(Name name, Cards cards) {
        super(name, cards);
    }

    public static Dealer createDefaultDealer(Cards cards) {
        return new Dealer(new Name(DEFAULT_DEALER_NAME), cards);
    }

    @Override
    public List<Card> getOpenCards() {
        return cards.getFirstCard();
    }

    @Override
    public boolean isReceivable() {
        return cards.sum() <= RECEIVE_SIZE && !isBlackjack();
    }
}
