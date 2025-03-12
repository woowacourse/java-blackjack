package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Player {

    private static final PlayerName DEALER_NAME = new PlayerName("딜러");

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isDealerHit() {
        return this.getGameScore().isDealerHit();
    }

    @Override
    public List<Card> getOpenedCards() {
        return List.of(getCards().getFirst());
    }
}
