package domain.player;

import domain.card.Deck;
import java.util.ArrayList;
import java.util.List;

public class Players {

    private static final int MAX_SIZE = 5;

    private final Dealer dealer;
    private final List<User> users;


    public Players(Dealer dealer, List<User> users) {
        validateSize(users);
        validateUniqueNames(users);
        this.dealer = dealer;
        this.users = users;
    }

    private void validateSize(List<User> users) {
        if (users.isEmpty() || users.size() > MAX_SIZE) {
            throw new IllegalArgumentException(users.size() + ": 유저 수는 1인 이상 5인 이하여야 합니다.");
        }
    }

    private void validateUniqueNames(List<User> users) {
        if (users.stream()
                .map(Player::getName)
                .distinct()
                .count() != users.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public void distributeInitialCards(Deck deck) {
        dealer.receiveInitialCards(deck);
        for (User user : users) {
            user.receiveInitialCards(deck);
        }
    }

    public void openInitialCards() {
        dealer.openInitialCards();
        for (User user : users) {
            user.openInitialCards();
        }
    }

    public int size() {
        return 1 + users.size();
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>(List.of(dealer));
        players.addAll(users);
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<User> getUsers() {
        return users;
    }
}
