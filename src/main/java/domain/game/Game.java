package domain.game;

import domain.Command;
import domain.deck.TotalDeck;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static domain.Command.YES;
import static domain.game.State.*;

public class Game {
    private final TotalDeck totalDeck;
    private final Users users;

    public Game(TotalDeck totalDeck, Users users) {
        this.totalDeck = totalDeck;
        this.users = users;
        users.setStartCards(totalDeck);
    }

    public boolean continueInput() {
        return users.isCurrentUserPlayer();
    }

    public State hitOrStay(Command command) {
        if (YES == command) {
            users.addCardOfCurrentUser(totalDeck.getNewCard());
            return hitOrBust();
        }
        users.nextUser();
        return STAY;
    }

    private State hitOrBust() {
        if (users.currentUserBusted()) {
            users.nextUser();
            return BUST;
        }
        return HIT;
    }

    public boolean isDealerCardAddCondition() {
        return users.isDealerCardAddCondition();
    }

    public void addDealerCard() {
        users.addDealerCard(totalDeck.getNewCard());
    }

    public PlayerResults generatePlayerResults() {
        Map<Player, Result> playerResults = users.getPlayers()
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        users::generatePlayerResult,
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
        return new PlayerResults(playerResults);
    }

    public User getCurrentPlayer() {
        return users.getCurrentUser();
    }
}
