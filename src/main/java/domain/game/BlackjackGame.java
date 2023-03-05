package domain.game;

import domain.strategy.NumberGenerator;
import domain.user.People;
import domain.user.Player;
import view.dto.PlayerParameter;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlackjackGame {

    private static final int START_HIT_COUNT = 2;
    private static final String DEALER_NAME = "딜러";

    private final Deck deck;
    private final People people;

    public BlackjackGame(List<String> playerNames, NumberGenerator numberGenerator) {
        this.deck = new Deck(numberGenerator);
        this.people = new People(playerNames, DEALER_NAME);
    }

    public void letDealerHitUntilThreshold() {
        people.letDealerHitUntilThreshold(deck);
    }

    public boolean dealerNeedsHit() {
        return people.dealerNeedsHit();
    }

    public void startHit() {
        for (int i = 0; i < START_HIT_COUNT; i++) {
            people.letPlayersToHit(deck);
        }
    }

    public Map<Player, GameResult> getGameResultForAllPlayer() {
        return people.makeGameResultForAllPlayer();
    }

    public Map<GameResult, Integer> getDealerRecord() {
        return people.getDealerRecord(people.makeGameResultForAllPlayer());
    }

    public People getPeople() {
        return people;
    }

    public void hitByCommand(Function<String, Command> function, Consumer<PlayerParameter> consumer) {
        people.hitByCommandAllPlayers(function, consumer, deck);
    }
}
