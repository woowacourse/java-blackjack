package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameManger {
    private static final int USER_MIN_COUNT = 1;
    private static final int USER_MAX_COUNT = 7;
    private static final int INIT_CARD_NUMBER = 2;

    private final List<Player> users;
    private final Dealer dealer;
    private final CardDeck cardDeck;

    public GameManger(List<Player> users, Dealer dealer, CardDeck cardDeck) {
        validate(users);
        this.users = new ArrayList<>(users);
        this.dealer = dealer;
        this.cardDeck = cardDeck;
    }

    private void validate(List<Player> users) {
        long distinctUserCount = users.stream()
                .map(Player::getName)
                .distinct()
                .count();

        if (distinctUserCount != users.size()) {
            throw new IllegalArgumentException("유저는 중복될 수 없습니다.");
        }
        if (users.isEmpty() || users.size() > USER_MAX_COUNT) {
            throw new IllegalArgumentException("유저는 " + USER_MIN_COUNT + "명 이상 " + USER_MAX_COUNT + "명 이하로 등록해야 합니다.");
        }
    }

    public void firstHandOutCard() {
        for (int count = 0; count < INIT_CARD_NUMBER; count++) {
            users.forEach(user -> user.drawCard(cardDeck.drawCard()));
            dealer.drawCard(cardDeck.drawCard());
        }
    }

    public Player findUserByUsername(String name) {
        return users.stream()
                .filter(player -> player.hasName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    }

    public User getDealer() {
        return this.dealer;
    }

    public TrumpCard handOutCard() {
        return cardDeck.drawCard();
    }

    private GameResult compareScore(User player) {
        int dealerScore = dealer.calculateScore();
        int playerScore = player.calculateScore();

        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (dealerScore < playerScore) {
            return GameResult.WIN;
        }
        if (dealerScore > playerScore) {
            return GameResult.LOSE;
        }
        return compareSameScore(player);
    }

    private GameResult compareSameScore(User player) {
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public Map<User, GameResult> calculatePlayerScore() {
        Map<User, GameResult> gameResult = new LinkedHashMap<>();
        if (dealer.isBust()) {
            users.forEach((user) -> putGameResultBust(user, gameResult));
            return gameResult;
        }
        users.forEach((user) -> gameResult.put(user, compareScore(user)));
        return gameResult;
    }

    private void putGameResultBust(User user, Map<User, GameResult> gameResult) {
        if (user.isBust()) {
            gameResult.put(user, GameResult.LOSE);
            return;
        }
        gameResult.put(user, GameResult.WIN);
    }

    public Map<GameResult, Integer> calculateDealerScore() {
        Map<GameResult, Integer> gameResult = new HashMap<>();
        Map<User, GameResult> userGameResultMap = calculatePlayerScore();

        gameResult.put(GameResult.LOSE, getResultStateCount(userGameResultMap, GameResult.WIN));
        gameResult.put(GameResult.WIN, getResultStateCount(userGameResultMap, GameResult.LOSE));
        gameResult.put(GameResult.DRAW, getResultStateCount(userGameResultMap, GameResult.DRAW));

        return gameResult;
    }

    private int getResultStateCount(Map<User, GameResult> gameResult, GameResult status) {
        return (int) gameResult.entrySet().stream()
                .filter(entry -> entry.getValue() == status)
                .count();
    }
}
