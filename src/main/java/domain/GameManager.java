package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameManager {
    public final static int MAX_PLAYER = 7;
    public final static int MAX_RANGE_HAND_OUT_CARD = 2;
    public final static int MIN_RANGE_HAND_OUT_CARD = 0;
    private final Users users;
    private final User dealer;
    private final TrumpCardManager trumpCardManager;

    public GameManager(List<String> names, TrumpCardManager trumpCardManager) {
        validate(names);
        this.users = new Users(names.stream()
                .map(Player::new)
                .collect(Collectors.toList()));
        this.dealer = new Dealer("딜러");
        this.trumpCardManager = trumpCardManager;
    }

    private void validate(List<String> names) {
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
            dealer.receiveCard(trumpCardManager.drawCard());
        }
    }

    public void drawMoreCard(final User user) {
        user.receiveCard(trumpCardManager.drawCard());
    }

    public User findUserByUsername(String name) {
        return users.findByUserName(name);
    }

    public User getDealer() {
        return this.dealer;
    }

    public GameResult compare(User player) {
        if (player.isBurst()) {
            return GameResult.LOSE;
        }
        if (dealer.getCardDeck().calculateScore() < player.getCardDeck().calculateScore()) {
            return GameResult.WIN;
        }
        if (dealer.getCardDeck().calculateScore() > player.getCardDeck().calculateScore()) {
            return GameResult.LOSE;
        }
        return compareSameScore(player);
    }

    private GameResult compareSameScore(User player) {
        if (dealer.getCardDeck().isBlackjack() && !player.getCardDeck().isBlackjack()) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public Map<User, GameResult> createGameResult() {
        Map<User, GameResult> gameResult = new LinkedHashMap<>();
        if (dealer.isBurst()) {
            users.getUsers().forEach((user) -> putGameResultBurst(user, gameResult));
            return gameResult;
        }
        users.getUsers().forEach((user) -> gameResult.put(user, compare(user)));
        return gameResult;
    }

    private void putGameResultBurst(User user, Map<User, GameResult> gameResult) {
        if (user.isBurst()) {
            gameResult.put(user, GameResult.LOSE);
            return;
        }
        gameResult.put(user, GameResult.WIN);
    }
}
