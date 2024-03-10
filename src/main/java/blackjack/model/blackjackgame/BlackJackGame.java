package blackjack.model.blackjackgame;

import blackjack.model.deck.DeckManager;
import blackjack.model.participants.Dealer;
import blackjack.model.participants.Player;
import blackjack.model.results.DealerResult;
import blackjack.model.results.PlayerResult;
import blackjack.model.results.Result;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private final Dealer dealer;
    private final List<Player> players;
    private final DeckManager deckManager;

    public BlackJackGame(Dealer dealer, List<Player> players, DeckManager deckManager) {
        this.dealer = dealer;
        this.deckManager = deckManager;
        this.players = new ArrayList<>(players);
    }

    public void distributeCards() {
        dealer.addCards(deckManager.drawCards());
        players.forEach(player -> player.addCards(deckManager.drawCards()));
    }

    public void update(int index) {
        Player player = players.get(index);
        player.addCard(deckManager.drawCard());
    }

    public boolean checkDealerState() {
        return dealer.checkDrawCardState();
    }

    public void updateDealer() {
        dealer.addCard(deckManager.drawCard());
    }

    public PlayerResult calculatePlayerResults() {
        Map<Player, Result> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, player.getResult(dealer.getCards())));
        return new PlayerResult(result);
    }

    public DealerResult calculateDealerResults(PlayerResult playerResult) {
        return new DealerResult(playerResult);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
