package blackjack.domain;

import blackjack.domain.betting.BettingAreas;
import blackjack.domain.card.Deck;

public class GameTable {

    private final Deck deck;
    private final BettingAreas bettingAreas;

    public GameTable(BettingAreas bettingAreas) {
        this.deck = new Deck();
        this.bettingAreas = bettingAreas;
    }

    public Deck getDeck() {
        return deck;
    }
}
