package blackjack.model.blackjackgame;

import blackjack.model.deck.CardDeck;
import blackjack.model.participants.Dealer;
import blackjack.model.participants.Player;
import blackjack.model.results.PlayerProfits;
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
        Map<Player, Money> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, player.evaluateProfit(dealer.getCards())));
        return new PlayerProfits(result);
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
