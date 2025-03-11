package domain;

import domain.card.CardGenerator;
import domain.gamer.Dealer;
import domain.gamer.GamerGenerator;
import domain.gamer.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static domain.GameResult.calculateResult;
import static domain.GameResult.getAllGameResults;

public class GameManager {
    public static final int LIMIT = 21;
    public static final int START_RECEIVE_CARD = 2;

    private final Dealer dealer;
    private final List<Player> players;

    public GameManager(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static GameManager create(final List<String> playerNames, CardGenerator cardGenerator) {
        Dealer dealer = GamerGenerator.generateDealer(cardGenerator);
        List<Player> players = GamerGenerator.generatePlayer(playerNames, cardGenerator);
        return new GameManager(dealer, players);
    }

    public void initOpeningCards() {
        dealer.receiveCard(START_RECEIVE_CARD);
        players.forEach(player -> player.receiveCard(START_RECEIVE_CARD));
    }

    public Map<GameResult, Integer> calculateDealerGameResult() {
        final List<GameResult> playerGameResult = calculatePlayerGameResult().values().stream().toList();
        return getAllGameResults().stream().filter(playerGameResult::contains).collect(Collectors.toMap(GameResult::swapGameResult, result -> Collections.frequency(playerGameResult, result), (newResult, oldResult) -> oldResult));
    }

    public Map<String, GameResult> calculatePlayerGameResult() {
        Map<String, GameResult> resultMap = new HashMap<>();
        for (Player player : players) {
            resultMap.put(player.getName(), calculateResult(dealer, player));
        }
        return resultMap;
    }

    public void dealerHitUntilStand() {
        dealer.hitCardUntilStand();
    }

    public void dealCardToPlayer(Player player) {
        player.receiveCard();
    }

    public int getDealerHitCount() {
        return dealer.getReceivedCardCount();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Player> getAbleToHitPlayers() {
        return players.stream().filter(player -> !player.isBust()).toList();
    }
}
