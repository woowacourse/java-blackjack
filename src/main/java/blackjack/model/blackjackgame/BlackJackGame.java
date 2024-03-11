package blackjack.model.blackjackgame;

import blackjack.model.deck.CardDeck;
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
    private final CardDeck cardDeck;

    public BlackJackGame(Dealer dealer, List<Player> players, CardDeck cardDeck) {
        this.dealer = dealer;
        this.cardDeck = cardDeck;
        this.players = new ArrayList<>(players);
    }

    public void distributeCards() {
        dealer.addCards(cardDeck.drawCards());
        players.forEach(player -> player.addCards(cardDeck.drawCards()));
    }

    public void update(int index) {
        Player player = players.get(index);
        player.addCard(cardDeck.drawCard());
    }

    public boolean checkDealerState() {
        return dealer.canHit();
    }

    public void updateDealer() {
        dealer.addCard(cardDeck.drawCard());
    }

    public PlayerResult calculatePlayerResults() {
        Map<Player, Result> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, player.getResult(dealer.getCards())));
        return new PlayerResult(result);
    }

    public DealerResult calculateDealerResults(PlayerResult playerResult) {
        return dealer.getResult(playerResult);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
