package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameManager {
    public final static int MAX_PLAYER = 7;

    private final List<User> users = new ArrayList<>();
    private final User dealer;

    public GameManager(List<String> names) {
        validate(names);
        for (String name : names) {
            users.add(new Player(name));
        }
        this.dealer = new Dealer("딜러");
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
        for (int count = 0; count < 2; count++) {
            users.forEach(User::drawCard);
            dealer.drawCard();
        }
    }

    public User findUserByUsername(String name) {
        return users.stream()
                .filter(user -> user.has(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
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
            users.forEach((user) -> putGameResultBurst(user, gameResult));
            return gameResult;
        }
        users.forEach((user) -> gameResult.put(user, compare(user)));
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
