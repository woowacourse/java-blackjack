package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.GameResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BlackjackGame {
    private static final BlackjackGame INSTANCE = new BlackjackGame();

    private BlackjackGame() {}

    public static BlackjackGame getInstance() {
        return INSTANCE;
    }

    public Dealer createDealer() {
        Deck deck = Deck.createUnShuffled();
        return Dealer.newInstance(deck);
    }

    public Players createPlayers(List<String> playerNames) {
        return new Players(playerNames);
    }

    public void dealInitCards(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            player.receiveInitCards(dealer.dealInit());
        }
        dealer.receiveInitCards(dealer.dealInit());
    }

    public Map<Player, GameResult> getPlayerResults(Players players, Dealer dealer) {
        Map<Player, GameResult> playerResults = new HashMap<>();
        for (Player player : players.getPlayers()) {
            playerResults.put(player, compareScore(dealer, player));
        }

        return playerResults;
    }

    private GameResult compareScore(Dealer dealer, Player player) {
        return GameResult.of(dealer, player);
    }

    public Map<GameResult, Long> getDealerResult(Map<Player, GameResult> playerResults) {
        return playerResults.values().stream()
                .map(GameResult::reverse)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
