package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Users {
    private final List<User> users;
    private User currentUser;

    public Users(List<Player> users) {
        this.users = new ArrayList<>(users);
        this.users.add(new Dealer());
        currentUser = this.users.get(0);
    }

    public Stream<User> stream() {
        return users.stream();
    }

    public List<Card> showCurrentUserDeck() {
        return currentUser.showUserDeck();
    }

    public void addCardOfCurrentUser(Card card) {
        currentUser.addCard(card);
    }

    public void nextUser() {
        currentUser = users.get(users.indexOf(currentUser) + 1);
    }

    public boolean isCurrentUserPlayer() {
        return currentUser instanceof Player;
    }
}
