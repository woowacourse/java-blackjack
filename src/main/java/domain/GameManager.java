package domain;

import domain.user.Betting;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GameManager {
    public final static int MAX_RANGE_HAND_OUT_CARD = 2;
    public final static int MIN_RANGE_HAND_OUT_CARD = 0;

    private final List<Player> players;
    private final Dealer dealer;
    private final TrumpCardManager trumpCardManager;

    public GameManager(List<Player> players, Dealer dealer, TrumpCardManager trumpCardManager) {
        this.players = players;
        this.dealer = dealer;
        this.trumpCardManager = trumpCardManager;
    }

    public static GameManager initailizeGameManager(Map<String, Betting> playerBetting,
                                                    TrumpCardManager trumpCardManager) {

        List<Player> players = playerBetting.entrySet().stream()
                .map(entry -> new Player(entry.getKey(), entry.getValue()))
                .toList();
        Dealer dealer = new Dealer();
        return new GameManager(players, dealer, trumpCardManager);
    }

    public void firstHandOutCard() {
        for (int count = MIN_RANGE_HAND_OUT_CARD; count < MAX_RANGE_HAND_OUT_CARD; count++) {
            dealer.receiveCard(trumpCardManager.drawCard());
            players.forEach(player -> player.receiveCard(trumpCardManager.drawCard()));
        }
    }

    public void drawMoreCard(final User user) {
        user.receiveCard(trumpCardManager.drawCard());
    }

    public Map<User, Long> createGameResult() {
        Map<User, Long> gameResult = new LinkedHashMap<>();
        if (dealer.isBurst()) {
            players.forEach((user) -> putGameResultBurst(user, gameResult));
            return gameResult;
        }
        players.forEach((player) -> gameResult.put(player, calculateProfit(player, dealer)));
        return gameResult;
    }

    private Long calculateProfit(Player player, Dealer dealer) {
        return player.calculateBettingResult(compare(player, dealer));
    }

    public GameResult compare(final Player player, final Dealer dealer) {
        if (player.isBurst()) {
            return GameResult.LOSE;
        }
        if (dealer.userScore() < player.userScore() && player.getCardDeck().isBlackjack()) {
            return GameResult.BLACKJACK;
        }
        if (dealer.userScore() < player.userScore()) {
            return GameResult.WIN;
        }
        if (dealer.userScore() > player.userScore()) {
            return GameResult.LOSE;
        }
        return compareSameScore(player, dealer);
    }

    private GameResult compareSameScore(final Player player, final Dealer dealer) {
        if (dealer.getCardDeck().isBlackjack() && !player.getCardDeck().isBlackjack()) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    private void putGameResultBurst(final Player player, final Map<User, Long> gameResult) {
        if (player.isBurst()) {
            gameResult.put(player, player.calculateBettingResult(GameResult.LOSE));
            return;
        }
        if (player.getCardDeck().isBlackjack()) {
            gameResult.put(player, player.calculateBettingResult(GameResult.BLACKJACK));
            return;
        }
        gameResult.put(player, player.calculateBettingResult(GameResult.WIN));
    }

    public long calculateDealerProfit(final Map<User, Long> gameResult) {
        long amount = gameResult.entrySet().stream()
                .mapToLong(Entry::getValue)
                .sum();
        return -amount;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Player findPlayerByUsername(final String playerName) {
        return players.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않는 닉네임 입니다."));
    }
}

