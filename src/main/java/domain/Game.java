package domain;

import domain.user.Player;
import domain.user.User;
import domain.user.UserDeck;
import domain.user.Users;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static domain.State.*;

public class Game {
    private final static String HIT_COMMAND = "y";
    private final static String STAY_COMMAND = "n";
    private final static int DEALER_CARD_CONDITION = 16;
    private final TotalDeck totalDeck;
    private final Users users;

    public Game(TotalDeck totalDeck, Users users) {
        this.totalDeck = totalDeck;
        this.users = users;
        users.setStartCards(totalDeck);
    }

    public State hitOrStay(String command) {
        if (HIT_COMMAND.equals(command)) {
            users.addCardOfCurrentUser(totalDeck.getNewCard());
            return hitOrBust();
        }
        if (STAY_COMMAND.equals(command)) {
            users.nextUser();
            return STAY;
        }
        throw new IllegalArgumentException("입력은 y, n만 가능합니다.");
    }

    private State hitOrBust() {
        if (users.busted()) {
            users.nextUser();
            return BUST;
        }
        return HIT;
    }

    public UserDeck showCurrentUserDeck() {
        return users.showCurrentUserDeck();
    }

    public boolean continueInput() {
        return users.isCurrentUserPlayer();
    }

    public void addDealerCard() {
        users.addDealerCard(totalDeck.getNewCard());
    }

    public boolean addDealerCardCondition() {
        return users.getDealerCardSum() <= DEALER_CARD_CONDITION;
    }

    public PlayerResults generatePlayerResults() {
        Map<Player, Result> playerResults = users.getPlayers().stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        users::generatePlayerResult
                ));
        return new PlayerResults(playerResults);
    }

    public User getCurrentPlayer() {
        return users.getCurrentPlayer();
    }
}
