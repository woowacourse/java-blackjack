package domain.participant;

import domain.ExceptionMessage;
import domain.Result;
import domain.RoundResult;
import domain.Score;
import domain.card.Card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> players) {
        validate(players);
        return new Players(players.stream()
                .map(name -> new Player(name))
                .toList());
    }

    private static void validate(List<String> players) {
        HashSet<String> uniqueNames = new HashSet<>(players);

        if (uniqueNames.size() != players.size()) {
            throw new IllegalArgumentException(ExceptionMessage.ALREADY_EXIST_NAME.getMessage());
        }
    }

    public void giveCardsToEachPlayers(Function<Integer, List<Card>> getCard, int quantity) {
        for (Player player : players) {
            player.addCards(getCard.apply(quantity));
        }
    }

    public void hitStandEachPlayers(Function<Player, Boolean> hitStandDecisionFunc, Supplier<Card> getCard,
                                    Consumer<Player> printResultFunc) {
        for (Player player : players) {
            while (true) {
                if (!hitStandDecisionFunc.apply(player)) {
                    printResultFunc.accept(player);
                    break;
                }
                player.addCard(getCard.get());
                printResultFunc.accept(player);
            }
        }
    }

    public List<RoundResult> getResults(Score dealerSum) {
        List<RoundResult> roundResults = new ArrayList<>();
        for (Player player : players) {
            Result result = Result.judge(player.getTotalSum(), dealerSum);
            roundResults.add(new RoundResult(player, result));
        }
        return roundResults;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    private void addPlayers(List<String> names) {
        for (String name : names) {
            addPlayer(name);
        }
    }

    private void addPlayer(String name) {
        players.add(new Player(name));
    }
}
