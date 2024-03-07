package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    private final Dealer dealer;
    private final List<Player> players;
    private final CardGenerator cardGenerator;

    public BlackJackGame(Dealer dealer, List<Player> players, CardGenerator cardGenerator) {
        this.dealer = dealer;
        this.cardGenerator = cardGenerator;
        this.players = new ArrayList<>(players);
    }

    public void distributeCards() {
        dealer.addCards(cardGenerator);
        players.forEach(player -> player.addCards(cardGenerator.drawCards()));
    }

    public void update(int index) {
        Player player = players.get(index);
        player.addCard(cardGenerator.drawCard());
    }

    public boolean checkDealerState() {
        return dealer.isScoreLessThanStandard();
    }

    public void updateDealer() {
        dealer.addCard(cardGenerator);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
