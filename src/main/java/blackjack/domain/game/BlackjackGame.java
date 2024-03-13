package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.GameResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BlackjackGame {
    private static final int INIT_CARD_COUNT = 2;

    private final Deck deck;

    private BlackjackGame(Deck deck) {
        this.deck = deck;
    }

    public static BlackjackGame create() {
        return new BlackjackGame(Deck.createShuffled());
    }

    public Dealer createDealer() {
        return Dealer.newInstance();
    }

    public Players createPlayers(List<String> playerNames) {
        return new Players(playerNames);
    }

    public void dealInitCards(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            player.receiveInitCards(deck.drawCards(INIT_CARD_COUNT));
        }
        dealer.receiveInitCards(deck.drawCards(INIT_CARD_COUNT));
    }

    public boolean isHit(Player player, boolean isHit) {
        return !player.isBust() && isHit;
    }

    public boolean isHit(Dealer dealer) {
        return dealer.hasHitScore();
    }

    public void hit(Gamer gamer) {
        gamer.receiveCard(deck.drawCard());
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
