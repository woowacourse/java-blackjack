package blackjack.fixture;

import blackjack.domain.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.stategy.NoShuffleStrategy;

public enum PlayerFixture {

    CHOCO(new Player("choco", new Dealer(new Deck(new NoShuffleStrategy())))),
    CLOVER(new Player("clover", new Dealer(new Deck(new NoShuffleStrategy())))),
    ;

    private final Player player;

    PlayerFixture(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
