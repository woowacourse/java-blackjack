package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public class Dealer extends Player {

    private static final PlayerName DEALER_NAME = new PlayerName("딜러");

    public Dealer(Cards cards) {
        super(DEALER_NAME, cards);
    }

    public boolean isDealerHit() {
        return this.getGameScore().isDealerHit();
    }

    @Override
    public List<Card> getOpenedCards() {
        return List.of(getCards().getFirst());
    }
}
