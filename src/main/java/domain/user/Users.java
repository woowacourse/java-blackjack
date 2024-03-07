package domain.user;

import domain.Result;
import domain.TotalDeck;
import domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static domain.Result.*;

public class Users {
    private static final int BLACK_JACK_CONDITION = 21;
    private static final int DEALER_INDEX = 0;
    private static final int FIRST_PLAYER_INDEX = 1;
    private final List<User> users;
    private User currentUser;

    public Users(List<Player> players) {
        this.users = new ArrayList<>();
        users.add(new Dealer());
        users.addAll(players);
        currentUser = users.get(FIRST_PLAYER_INDEX);
    }

    public UserDeck showCurrentUserDeck() {
        return currentUser.getUserDeck();
    }

    public void addCardOfCurrentUser(Card card) {
        currentUser.addCard(card);
    }

    public void nextUser() {
        if (users.get(users.size() - 1).equals(currentUser)) {
            currentUser = getDealer();
            return;
        }
        currentUser = users.get(users.indexOf(currentUser) + 1);
    }

    public boolean isCurrentUserPlayer() {
        return currentUser instanceof Player;
    }

    public void addDealerCard(Card card) {
        getDealer().addCard(card);
    }

    public int getDealerCardSum() {
        return getDealer().sumUserDeck();
    }

    public Result generatePlayerResult(Player player) {
        if (player.sumUserDeck() < getDealerCardSum()) {
            return LOSE;
        }
        if (player.sumUserDeck() > BLACK_JACK_CONDITION) {
            return LOSE;
        }
        if (player.sumUserDeck() == getDealerCardSum()) {
            return DRAW;
        }
        return WIN;
    }

    public List<Player> getPlayers() {
        List<User> players = users.subList(FIRST_PLAYER_INDEX, users.size());
        return players.stream()
                .map(user -> (Player) user)
                .collect(Collectors.toList());
    }

    public Dealer getDealer() {
        return (Dealer) users.get(DEALER_INDEX);
    }

    public void setStartCards(TotalDeck totalDeck) {
        for (User user : users) {
            user.addCard(totalDeck.getNewCard());
            user.addCard(totalDeck.getNewCard());
        }
    }

    public User getCurrentPlayer() {
        return currentUser;
    }

    public boolean busted() {
        return currentUser.sumUserDeck() > BLACK_JACK_CONDITION;
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
