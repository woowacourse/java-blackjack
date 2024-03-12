package domain.game;

import domain.Command;
import domain.deck.TotalDeck;
import domain.deck.UserDeck;
import domain.user.Name;
import domain.user.Player;
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

    public Index giveIndexOfGame() {
        return users.makeIndex();
    }

    public Name getNameByIndex(Index index) {
        return users.getNameByIndex(index);
    }

    public UserDeck getUserDeckByIndex(Index index) {
        return users.geUserDeckByIndex(index);
    }

    public State determineState(Command command, Index index) {
        if (YES == command) {
            users.addCardOfCurrentUser(totalDeck.getNewCard(), index);
            return hitOrBust(index);
        }
        return STAY;
    }


    private State hitOrBust(Index index) {
        if (users.currentUserBusted(index)) {
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
