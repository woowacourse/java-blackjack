package blackjack.domain.player;


import blackjack.domain.card.Card;
import blackjack.domain.common.Name;
import blackjack.domain.card.Cards;
import java.util.List;

public class GamePlayer extends Player implements CardReceivable {
    private static final Integer RECEIVE_SIZE = 21;

    public GamePlayer(Name name, Cards cards) {
        super(name, cards);
    }

    @Override
    public List<Card> getOpenCards() {
        return getCards();
    }

    @Override
    public boolean isReceivable() {
        return cards.sum() < RECEIVE_SIZE;
    }
}
