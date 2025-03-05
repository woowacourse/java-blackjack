package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GameManger {
    private final List<User> users = new ArrayList<>();
    private final User dealer;

    public GameManger(List<String> names) {
        validate(names);
        for (String name : names) {
            users.add(new Player(name));
        }
        this.dealer = new Dealer("dealer");
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
}
