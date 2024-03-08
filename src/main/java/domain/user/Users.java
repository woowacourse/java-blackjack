package domain.user;

import static domain.game.Result.LOSE;
import static domain.game.Result.WIN;

import domain.TotalDeck;
import domain.card.Card;
import domain.game.Result;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        if (lastPlayer()) {
            currentUser = getDealer();
            return;
        }
        currentUser = users.get(nextUserIndex());
    }

    private boolean lastPlayer() {
        return users.get(users.size() - 1).equals(currentUser);
    }

    private int nextUserIndex() {
        return users.indexOf(currentUser) + 1;
    }

    public boolean isDealerCardAddCondition() {
        return getDealer().isAddCondition();
    }

    public void addDealerCard(Card card) {
        getDealer().addCard(card);
    }

    public Result generatePlayerResult(Player player) {
        if (player.busted()) {
            return LOSE;
        }
        if (getDealer().busted()) {
            return WIN;
        }
        return Result.compare(player.sumUserDeck(), getDealer().sumUserDeck());
    }

    public boolean currentUserBusted() {
        return currentUser.busted();
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

    private void validateDuplicatedName(List<Player> players) {
        long count = players.stream()
                .map(player -> player.getName().value())
                .distinct()
                .count();
        if (players.size() != count) {
            throw new IllegalArgumentException("중복된 이름은 허용하지 않습니다.");
        }
    }
}
