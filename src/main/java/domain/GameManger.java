package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameManger {
    public final static int WIN = 1;
    public final static int LOSE = 2;
    public final static int MOO = 3;

    private final List<Player> users = new ArrayList<>();
    private final Dealer dealer;
    private final CardDeck cardDeck;

    public GameManger(List<String> names, Dealer dealer, CardDeck cardDeck) {
        validate(names);
        for (String name : names) {
            users.add(new Player(name));
        }
        this.dealer = dealer;
        this.cardDeck = cardDeck;
    }

    private void validate(List<String> names) {
        Set<String> distinctNames = new HashSet<>(names);
        if (names.isEmpty() || names.size() > 7) {
            throw new IllegalArgumentException("유저는 1명 이상 7명 이하로 등록해야 합니다.");
        }
        if (distinctNames.size() != names.size()) {
            throw new IllegalArgumentException("유저는 중복될 수 없습니다.");
        }
    }

    public void firstHandOutCard() {
        for (int count = 0; count < 2; count++) {
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

    public int compareScore(User player) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.getCardHand().calculateScore() < player.getCardHand().calculateScore()) {
            return WIN;
        }
        if (dealer.getCardHand().calculateScore() > player.getCardHand().calculateScore()) {
            return LOSE;
        }
        return compareSameScore(player);
    }

    private int compareSameScore(User player) {
        if (dealer.getCardHand().isBlackjack() && !player.getCardHand().isBlackjack()) {
            return LOSE;
        }
        return MOO;
    }

    public Map<User, Integer> createGameResult() {
        Map<User, Integer> gameResult = new LinkedHashMap<>();
        if (dealer.isBust()) {
            users.forEach((user) -> putGameResultBust(user, gameResult));
            return gameResult;
        }
        users.forEach((user) -> gameResult.put(user, compareScore(user)));
        return gameResult;
    }

    private void putGameResultBust(User user, Map<User, Integer> gameResult) {
        if (user.isBust()) {
            gameResult.put(user, LOSE);
            return;
        }
        gameResult.put(user, WIN);
    }
}
