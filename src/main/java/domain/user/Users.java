package domain.user;

import domain.TrumpCardManager;
import java.util.List;

public class Users {
    private final List<User> users;

    public Users(List<User> users) {
        this.users = users;
    }

    public List<Player> findPlayers() {
        return users.stream()
                .filter(user -> !user.isDealer())
                .map(user -> (Player) user)
                .toList();
    }

    public Dealer findDealer() {
        return users.stream()
                .filter(User::isDealer)
                .map(user -> (Dealer) user)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("딜러가 존재하지 않습니다"));
    }

    public Player findByPlayerName(String name) {
        return users.stream()
                .filter(user -> !user.isDealer())
                .filter(user -> user.hasName(name))
                .map(user -> (Player) user)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    }

    public String findDealerName() {
        return findDealer().getName();
    }
    
    public void userCardDraw(TrumpCardManager trumpCardManager) {
        users.forEach((user) -> user.receiveCard(trumpCardManager.drawCard()));
    }
}
