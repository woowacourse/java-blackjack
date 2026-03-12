package domain.participant;

import domain.Result;
import domain.RoundResult;
import domain.card.Deck;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Players {
    private static final String ALREADY_EXIST_NAME = "[ERROR] 이름은 중복될 수 없습니다.";

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
            throw new IllegalArgumentException(ALREADY_EXIST_NAME);
        }
    }

    public void giveCardsToEachPlayers(Deck deck, int quantity) {
        for (Player player : players) {
            player.addCards(deck.drawWithAmount(quantity));
        }
    }

    public void hitStandEachPlayers(Function<Player, Boolean> hitStandDecisionFunc, Deck deck,
                                    Consumer<Player> printResultFunc) {
        for (Player player : players) {
            while (true) {
                if (player.isBust()) {
                    break;
                }
                if (!hitStandDecisionFunc.apply(player)) {
                    printResultFunc.accept(player);
                    break;
                }
                player.addCard(deck.draw());
                printResultFunc.accept(player);
            }
        }
    }

    public List<RoundResult> getResults(Dealer dealer) {
        List<RoundResult> roundResults = new ArrayList<>();
        for (Player player : players) {
            Result result = Result.judge(player, dealer);
            roundResults.add(new RoundResult(player, result));
        }
        return roundResults;
    }

    public List<Player> getPlayers() {
        return players.stream()
                .map(player -> new Player(player))
                .toList();
    }
}
