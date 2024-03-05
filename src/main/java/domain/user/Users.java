package domain.user;

import domain.Result;
import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

    public UserDeck showCurrentUserDeck() {
        return currentUser.getUserDeck();
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

    public void addDealerCard(Card card) {
        getDealer().addCard(card);
    }

    public int getDealerCardSum() {
        User dealer = getDealer();
        return dealer.sumUserDeck();
    }

    public Result generatePlayerResult(Player player) {
        if (player.sumUserDeck() < getDealerCardSum()) {
            return Result.LOSE;
        }
        if (player.sumUserDeck() > 21) {
            return Result.LOSE;
        }
        return Result.WIN;
    }

    public List<Player> getPlayers() {
        List<User> users = this.users.subList(0, this.users.size() - 1);
        return users.stream()
                .map(user -> (Player) user)
                .collect(Collectors.toList());
    }

    private User getDealer() {
        return users.get(users.size() - 1);
    }
}
