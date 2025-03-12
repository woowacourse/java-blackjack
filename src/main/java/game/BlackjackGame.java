package game;

import deck.Deck;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import participant.Dealer;
import participant.Player;
import participant.Players;

public class BlackjackGame {

    private static final int INITIAL_COUNT = 2;

    public void distributeTwoCardsToParticipants(Dealer dealer, Players players, Deck deck) {
        for (int count = 0; count < INITIAL_COUNT; count++) {
            dealer.receiveCard(deck.draw());
            players.getPlayers().forEach(player -> player.receiveCard(deck.draw()));
        }
    }

    public void runPlayerTurn(Player player, Deck deck) {
        if (!player.canReceiveCard()) {
            return;
        }
        player.receiveCard(deck.draw());
    }

    public void runDealerTurn(Dealer dealer, Deck deck) {
        if (!dealer.canReceiveCard()) {
            return;
        }
        dealer.receiveCard(deck.draw());
    }

    public Map<Player, GameResult> calculatePlayersGameResults(Dealer dealer, Players players) {
        return players.getPlayers().stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> GameResult.judge(player, dealer),
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    public Map<GameResult, Integer> calculateDealerGameResults(Map<Player, GameResult> playerGameResults) {
        Map<GameResult, Integer> dealerResults = Arrays.stream(GameResult.values())
                .collect(Collectors.toMap(
                        gameResult -> gameResult,
                        gameResult -> 0,
                        (oldValue, newValue) -> oldValue,
                        () -> new EnumMap<>(GameResult.class)
                ));

        for (GameResult gameResult : playerGameResults.values()) {
            dealerResults.put(gameResult.inverse(), dealerResults.get(gameResult.inverse()) + 1);
        }

        return dealerResults;
    }
}
