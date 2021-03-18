package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.result.Result;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Names;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private Names names;
    private Players players;

    public BlackjackGame() {
        this.cardDeck = new CardDeck();
        this.dealer = new Dealer(cardDeck.generateUserDeck());
    }

    public Names getNames() {
        return names;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public List<Player> getPlayersList() {
        return players.getPlayers();
    }

    public void makeNames(String players) {
        names = new Names(players);
    }

    public void makePlayers(List<Double> money) {
        players = new Players(cardDeck, names, money);
    }

    public boolean isPlayerDraw(Player player, String input) {
        if (player.wantToDraw(input)) {
            player.draw(cardDeck);
            return true;
        }
        return false;
    }

    public void dealerDraw() {
        dealer.draw(cardDeck);
    }

    public Map<Player, Double> playerEarningResult() {
        Map<Player, Double> playerEarning = new HashMap<>();
        for (Player player : players.getPlayers()) {
            double earning = earningResult(dealer, player);
            playerEarning.put(player, earning);
        }
        return Collections.unmodifiableMap(playerEarning);
    }

    public double getDealerEarning(Map<Player, Double> playerEarning) {
        return playerEarning.values()
            .stream()
            .mapToDouble(Double::doubleValue)
            .sum();
    }

    private double earningResult(Dealer dealer, Player player) {
        double earning = player.getEarning();
        if (earning == player.getMoney()) {
            earning = player.getEarning(Result.compareScoreResult(player, dealer));
        }
        return earning;
    }
}
