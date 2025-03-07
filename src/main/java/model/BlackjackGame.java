package model;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.deck.Deck;
import model.deck.ShuffledDeckCreator;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;

public class BlackjackGame {

    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    public BlackjackGame(List<String> playerNames) {
        this.dealer = new Dealer();
        this.players = new Players(playerNames);
        this.deck = new Deck(new ShuffledDeckCreator());
    }

    public void startGame() {
        for (int i = 0; i < 2; i++) {
            dealer.receiveCard(deck.draw());
            players.getPlayers()
                    .forEach(player -> player.receiveCard(deck.draw()));
        }
    }

    public void runPlayerTurn(Player player) {
        if (!player.canReceiveCard()) {
            return;
        }
        player.receiveCard(deck.draw());
    }

    public void runDealerTurn() {
        dealer.receiveCard(deck.draw());
    }

    public Map<Player, GameResult> calculatePlayerGameResult() {
        return players.getPlayers().stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> GameResult.judge(player, dealer),
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    public Map<GameResult, Integer> calculateDealerGameResult(Map<Player, GameResult> playerGameResults) {
        Map<GameResult, Integer> dealerResults = new EnumMap<>(GameResult.class);

        for (GameResult gameResult : GameResult.values()) {
            dealerResults.put(gameResult, 0);
        }

        for (GameResult gameResult : playerGameResults.values()) {
            if (gameResult.equals(GameResult.WIN)) {
                dealerResults.put(GameResult.LOSE, dealerResults.get(GameResult.LOSE) + 1);
                continue;
            }
            if (gameResult.equals(GameResult.LOSE)) {
                dealerResults.put(GameResult.WIN, dealerResults.get(GameResult.WIN) + 1);
                continue;
            }
            dealerResults.put(GameResult.DRAW, dealerResults.get(GameResult.DRAW) + 1);
        }
        return dealerResults;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
