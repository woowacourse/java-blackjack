package blackjack.model.blackjackgame;

import blackjack.model.deck.CardDeck;
import blackjack.model.participants.Dealer;
import blackjack.model.participants.Player;
import blackjack.model.results.PlayerResult;
import blackjack.model.results.Result;
import blackjack.vo.Money;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private final Dealer dealer;
    private final List<Player> players;

    public BlackJackGame(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = new ArrayList<>(players);
    }

    public void distributeCards() {
        dealer.addCards(CardDeck.drawCards());
        players.forEach(player -> player.addCards(CardDeck.drawCards()));
    }

    public void update(int index) {
        Player player = players.get(index);
        player.addCard(CardDeck.drawCard());
    }

    public boolean checkDealerState() {
        return dealer.canHit();
    }

    public void updateDealer() {
        dealer.addCard(CardDeck.drawCard());
    }

    public PlayerResult calculatePlayerResults() {
        Map<Player, Result> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, player.getResult(dealer.getCards())));
        return new PlayerResult(result);
    }

    public Money calculateDealerProfit(PlayerResult playerResult) {
        return dealer.calculateDealerProfit(playerResult);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
