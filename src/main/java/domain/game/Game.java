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

    public State determineState(Command command, User user) {
        if (YES == command) {
            user.addCard(totalDeck.getNewCard());
            return hitOrBust(user);
        }
        return STAY;
    }

    private State hitOrBust(User user) {
        if (user.isBust()) {
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

    public DealerResult generateDealerResult() {
        Map<Result, Integer> dealerResult = users.getPlayers()
                .stream()
                .collect(Collectors.toMap(
                        player -> users.generatePlayerResult(player).reverse(),
                        value -> 1,
                        (oldValue, newValue) -> oldValue + 1,
                        LinkedHashMap::new
                ));
        return new DealerResult(dealerResult);
    }

}
