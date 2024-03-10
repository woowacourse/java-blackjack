package domain.user;

import domain.BlackjackException;
import domain.card.Card;
import domain.deck.TotalDeck;
import domain.game.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static domain.game.Result.LOSE;
import static domain.game.Result.WIN;

public class Users {
    private static final int DEALER_INDEX = 0;
    private static final int FIRST_PLAYER_INDEX = 1;
    private final List<User> users;
    private User currentUser;

    public Users(List<Player> players) {
        validateDuplicatedName(players);
        this.users = new ArrayList<>();
        users.add(new Dealer());
        users.addAll(players);
        currentUser = users.get(FIRST_PLAYER_INDEX);
    }

    private void validateDuplicatedName(List<Player> players) {
        long count = players.stream()
                .map(player -> player.getName().value())
                .distinct()
                .count();
        if (players.size() != count) {
            throw new BlackjackException("중복된 이름은 허용하지 않습니다.");
        }
    }

    public void setStartCards(TotalDeck totalDeck) {
        for (User user : users) {
            user.addCard(totalDeck.getNewCard());
            user.addCard(totalDeck.getNewCard());
        }
    }

    public boolean isCurrentUserPlayer() {
        return currentUser.isPlayer();
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

    public boolean isDealerCardAddCondition() {
        return getDealer().isDealerCardAddCondition();
    }

    public void addDealerCard(Card card) {
        getDealer().addCard(card);
    }

    public Result generatePlayerResult(Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        if (getDealer().isBust()) {
            return WIN;
        }
        return Result.compare(player.sumUserDeck(), getDealer().sumUserDeck());
    }

    public boolean currentUserBusted() {
        return currentUser.isBust();
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public User getCurrentUser() {
        return currentUser;
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
}
