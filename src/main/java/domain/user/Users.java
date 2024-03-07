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
    private static final int BLACK_JACK_CONDITION = 21;
    private final static int DEALER_CARD_CONDITION = 16;
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
            throw new IllegalArgumentException("중복된 이름은 허용하지 않습니다.");
        }
    }

    public void setStartCards(TotalDeck totalDeck) {
        for (User user : users) {
            user.addCard(totalDeck.getNewCard());
            user.addCard(totalDeck.getNewCard());
        }
    }

    public boolean isCurrentUserPlayer() {
        return currentUser instanceof Player;
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
        return sumDealerCard() <= DEALER_CARD_CONDITION;
    }

    public void addDealerCard(Card card) {
        getDealer().addCard(card);
    }

    private int sumDealerCard() {
        return getDealer().sumUserDeck();
    }

    public Result generatePlayerResult(Player player) {
        if (busted(player)) {
            return LOSE;
        }
        if (busted(getDealer())) {
            return WIN;
        }
        return Result.compare(player.sumUserDeck(), getDealer().sumUserDeck());
    }

    public boolean currentUserBusted() {
        return busted(currentUser);
    }

    private boolean busted(User user) {
        return user.sumUserDeck() > BLACK_JACK_CONDITION;
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
