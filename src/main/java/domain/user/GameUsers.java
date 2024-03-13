package domain.user;

import static domain.game.GameResult.LOSE;
import static domain.game.GameResult.WIN;

import domain.Deck;
import domain.game.GameResult;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameUsers {
    private final Players players;
    private final Dealer dealer;

    public GameUsers(Players players, Deck deck) {
        this.players = players;
        this.dealer = new Dealer();
        putStartCards(deck);
    }

    private void putStartCards(Deck deck) {
        players.putStartCards(deck);
        dealer.putStartCards(deck);
    }

    public Map<Player, GameResult> generatePlayerResults() {
        return players.getPlayers()
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        this::generatePlayerResult,
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
    }

    private GameResult generatePlayerResult(Player player) {
        if (player.busted() || dealerBlackjackOnly(player)) {
            return LOSE;
        }
        if (dealer.busted() || playerBlackjackOnly(player)) {
            return WIN;
        }
        return GameResult.compare(player.sumHand(), dealer.sumHand());
    }

    private boolean dealerBlackjackOnly(Player player) {
        return !player.isBlackJack() && dealer.isBlackJack();
    }

    private boolean playerBlackjackOnly(Player player) {
        return player.isBlackJack() && !dealer.isBlackJack();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
