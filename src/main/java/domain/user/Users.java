package domain.user;

import domain.TrumpCardManager;
import java.util.ArrayList;
import java.util.List;

public class Users {
    private final List<User> users;

    public Users(List<User> users) {
        this.users = new ArrayList<>(users);
    }

    public void userCardDraw(TrumpCardManager trumpCardManager) {
        users.forEach((user) -> user.receiveCard(trumpCardManager.drawCard()));
    }

    public User findByUserName(String name) {
        return users.stream()
            .filter(user -> user.hasName(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }
}
