package domain.game;

import static domain.Command.YES;
import static domain.game.State.BUST;
import static domain.game.State.HIT;
import static domain.game.State.STAY;

import domain.Command;
import domain.TotalDeck;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Game {
    private final TotalDeck totalDeck;
    private final Users users;

    public Game(TotalDeck totalDeck, Users users) {
        this.totalDeck = totalDeck;
        this.users = users;
        users.setStartCards(totalDeck);
    }

    public State hitOrStay(Command command, User user) {
        if (YES == command) {
            user.addCard(totalDeck.getNewCard());
            return hitOrBust(user);
        }
        return STAY;
    }

    private State hitOrBust(User user) {
        if (user.busted()) {
            return BUST;
        }
        return HIT;
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
}
