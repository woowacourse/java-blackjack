package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameManger {
    public final static int WIN = 1;
    public final static int LOSE = 2;
    public final static int MOO = 3;

    private final List<User> users = new ArrayList<>();
    private final User dealer;

    public GameManger(List<String> names) {
        validate(names);
        for (String name : names) {
            users.add(new Player(name));
        }
        this.dealer = new Dealer("딜러");
    }

    private void validate(List<String> names) {
        HashSet<String> distinctNames = new HashSet<>(names);
        if (names.isEmpty() || names.size() > 7) {
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

    public int compare(User player) {
        if (player.isBurst()) {
            return LOSE;
        }
        if (dealer.getCardDeck().calculateScore() < player.getCardDeck().calculateScore()) {
            return WIN;
        }
        if (dealer.getCardDeck().calculateScore() > player.getCardDeck().calculateScore()) {
            return LOSE;
        }
        return compareSameScore(player);
    }

    private int compareSameScore(User player) {
        if (dealer.getCardDeck().isBlackjack() && !player.getCardDeck().isBlackjack()) {
            return LOSE;
        }
        return MOO;
    }

    public Map<User, Integer> createGameResult() {
        Map<User, Integer> gameResult = new LinkedHashMap<>();
        if (dealer.isBurst()) {
            users.forEach((user) -> putGameResultBurst(user, gameResult));
            return gameResult;
        }
        users.forEach((user) -> gameResult.put(user, compare(user)));
        return gameResult;
    }

    private void putGameResultBurst(User user, Map<User, Integer> gameResult) {
        if (user.isBurst()) {
            gameResult.put(user, LOSE);
            return;
        }
        gameResult.put(user, WIN);
    }
}
