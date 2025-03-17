package domain;

import domain.user.Betting;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<Profit> createProfitResult() {
        List<Profit> profitResult = new ArrayList<>();
        if (dealer.isBurst()) {
            players.forEach((user) -> putGameResultBurst(user, profitResult));
            return profitResult;
        }
        players.forEach((player) -> profitResult.add(new Profit(player, calculateProfit(player, dealer))));
        return profitResult;
    }

    public void drawMoreCard(final User user) {
        user.receiveCard(trumpCardManager.drawCard());
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

    private void putGameResultBurst(final Player player, final List<Profit> profitResult) {
        if (player.isBurst()) {
            profitResult.add(new Profit(player, player.calculateBettingResult(GameResult.LOSE)));
            return;
        }
        if (player.getCardDeck().isBlackjack()) {
            profitResult.add(new Profit(player, player.calculateBettingResult(GameResult.BLACKJACK)));
            return;
        }
        profitResult.add(new Profit(player, player.calculateBettingResult(GameResult.WIN)));
    }

    public long calculateDealerProfitFromPlayers(final List<Profit> playerProfit) {
        return -playerProfit.stream()
                .mapToLong(Profit::profit)
                .sum();
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

