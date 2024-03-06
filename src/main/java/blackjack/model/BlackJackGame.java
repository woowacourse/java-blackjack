package blackjack.model;

import java.util.List;

public class BlackJackGame {
    private final Dealer dealer;
    private final List<Player> players;
    private final CardGenerator cardGenerator;

    public BlackJackGame(Dealer dealer, List<Player> players, CardGenerator cardGenerator) {
        this.dealer = dealer;
        this.players = players;
        this.cardGenerator = cardGenerator;
    }

    public void distributeCards() {
        dealer.addCards(cardGenerator);
        players.forEach(player -> player.addCards(cardGenerator));
    }
}
