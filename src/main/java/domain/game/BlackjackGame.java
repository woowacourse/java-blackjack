package domain.game;

import domain.strategy.NumberGenerator;
import domain.user.Dealer;
import domain.user.People;
import domain.user.Player;
import view.dto.PlayerDTO;

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

    public void startHit() {
        for (int i = 0; i < START_HIT_COUNT; i++) {
            people.letPlayersToHit(deck);
        }
    }

    public void letDealerHitUntilThreshold(Runnable outputDealerHitMessage) {
        if (people.dealerCanHit()) {
            outputDealerHitMessage.run();
            people.letDealerHitUntilThreshold(deck);
        }

    }

    public void hitAllPlayersByCommand(Function<String, String> inputCommand, Consumer<PlayerDTO> outputPlayer) {
        people.hitByCommandAllPlayers(inputCommand, outputPlayer, deck);
    }

    public Map<Player, GameResult> getGameResultForAllPlayer() {
        return people.makeGameResultForAllPlayer();
    }

    public Map<GameResult, Integer> getDealerRecord() {
        return people.getDealerRecord(people.makeGameResultForAllPlayer());
    }

    public List<Player> getPlayers() {
        return people.getPlayers();
    }

    public Dealer getDealer() {
        return people.getDealer();
    }

    public int getDealerSumHand() {
        return getDealer().sumHand();
    }
}
