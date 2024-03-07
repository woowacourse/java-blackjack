package blackjack.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        dealer.addCards(cardGenerator.drawCards());
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

    public GameResults calculateFinalResults() {
        Map<Player, ResultStatus> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, dealer.determineWinner(player)));
        return new GameResults(result);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
