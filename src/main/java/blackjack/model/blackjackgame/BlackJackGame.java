package blackjack.model.blackjackgame;

import blackjack.model.deck.CardDeck;
import blackjack.model.participants.Dealer;
import blackjack.model.participants.Player;
import blackjack.model.results.PlayerProfits;
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

    public PlayerProfits calculatePlayerProfits() {
        Map<Player, Money> profits = new LinkedHashMap<>();
        players.forEach(player -> {
            Result result = player.evaluateResult(dealer.getState());
            profits.put(player, player.calculateProfit(result));
        });
        return new PlayerProfits(profits);
    }

    public Money calculateDealerProfit(PlayerProfits playerProfits) {
        return dealer.calculateDealerProfit(playerProfits);
    }

    public void finishTurn(Player player) {
        player.finishTurn();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
