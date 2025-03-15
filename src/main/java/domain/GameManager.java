package domain;

import domain.user.Betting;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameManager {
    public final static int MAX_PLAYER = 7;
    public final static int MAX_RANGE_HAND_OUT_CARD = 2;
    public final static int MIN_RANGE_HAND_OUT_CARD = 0;

    private final Users users;
    private final TrumpCardManager trumpCardManager;

    public GameManager(Users users, TrumpCardManager trumpCardManager) {
        this.users = users;
        this.trumpCardManager = trumpCardManager;
    }

    public static GameManager initailizeGameManager(List<String> names, List<Long> playersBettingMoney,
                                                    TrumpCardManager trumpCardManager) {
        validateNames(names);
        List<User> users = IntStream.range(0, names.size())
                .mapToObj(i -> new Player(names.get(i), new Betting(playersBettingMoney.get(i))))
                .collect(Collectors.toList());
        Dealer dealer = new Dealer();
        users.add(dealer);
        return new GameManager(new Users(users), trumpCardManager);
    }

    private static void validateNames(List<String> names) {
        HashSet<String> distinctNames = new HashSet<>(names);
        if (names.isEmpty() || names.size() > MAX_PLAYER) {
            throw new IllegalArgumentException("유저는 1명 이상 7명 이하로 등록해야 합니다.");
        }
        if (distinctNames.size() != names.size()) {
            throw new IllegalArgumentException("유저는 중복될 수 없습니다.");
        }
    }

    public void firstHandOutCard() {
        for (int count = MIN_RANGE_HAND_OUT_CARD; count < MAX_RANGE_HAND_OUT_CARD; count++) {
            users.userCardDraw(trumpCardManager);
        }
    }

    public void drawMoreCard(final User user) {
        user.receiveCard(trumpCardManager.drawCard());
    }

    public Map<User, Long> createGameResult() {
        Map<User, Long> gameResult = new LinkedHashMap<>();
        Dealer dealer = users.findDealer();
        List<Player> players = users.findPlayers();
        if (dealer.isBurst()) {
            players.forEach((user) -> putGameResultBurst(user, gameResult));
            return gameResult;
        }
        players.forEach((player) -> gameResult.put(player, calculateProfit(player, dealer)));
        return gameResult;
    }

    private Long calculateProfit(Player player, Dealer dealer) {
        return player.cacluateBettingResult(compare(player, dealer));
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
            gameResult.put(player, player.cacluateBettingResult(GameResult.LOSE));
            return;
        }
        gameResult.put(player, player.cacluateBettingResult(GameResult.WIN));
    }

    public String findDealerName() {
        return users.findDealerName();
    }

    public Player findPlayerByUsername(final String name) {
        return users.findByPlayerName(name);
    }

    public Dealer getDealer() {
        return users.findDealer();
    }

    public long calculateDealerProfit(Map<User, Long> gameResult) {
        long amount = gameResult.entrySet().stream()
                .mapToLong(Entry::getValue)
                .sum();
        return -amount;
    }
}
