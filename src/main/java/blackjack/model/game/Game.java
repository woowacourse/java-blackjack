package blackjack.model.game;

import blackjack.model.cardgenerator.CardGenerator;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Players;

public class Game {
    private final Players players;
    private final Dealer dealer;
    private final CardGenerator cardGenerator;

    public Game(final Players players, final Dealer dealer, final CardGenerator cardGenerator) {
        this.players = players;
        this.dealer = dealer;
        this.cardGenerator = cardGenerator;
    }
}
