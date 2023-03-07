package domain.user;

import domain.game.Deck;
import domain.game.GameResult;
import view.dto.PlayerInfoDto;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class People {

    private final List<Player> players;
    private final Dealer dealer;

    public People(List<String> playerNames, String dealerName) {
        this.players = mapToPlayers(playerNames);
        this.dealer = mapToDealer(dealerName);
    }

    private List<Player> mapToPlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(playerName -> new Player(playerName, new Hand(Collections.emptyList())))
                .collect(Collectors.toList());
    }

    private Dealer mapToDealer(String dealerName) {
        return new Dealer(dealerName, new Hand(Collections.emptyList()));
    }

    public void letPlayersToHit(Deck deck) {
        for (Player player : players) {
            player.draw(deck.serve());
        }
        dealer.draw(deck.serve());
    }

    public void letDealerHitUntilThreshold(Deck deck) {
        while (dealer.canHit()) {
            dealer.draw(deck.serve());
        }
    }

    public Map<Player, GameResult> makeGameResultForAllPlayer() {
        Map<Player, GameResult> record = new HashMap<>();
        recordGameResult(record);

        return record;
    }

    private void recordGameResult(Map<Player, GameResult> record) {
        players.forEach(player -> record.put(player, GameResult.getResult(player, dealer)));
    }

    public Map<GameResult, Integer> getDealerRecord(Map<Player, GameResult> record) {
        return GameResult.makeDealerRecord(record);
    }

    public void hitByCommandAllPlayers(Function<String, String> inputCommand, Consumer<PlayerInfoDto> outputPlayer, Deck deck) {
        players.forEach(player -> player.hitByCommand(inputCommand, outputPlayer, deck));
    }

    public boolean dealerCanHit() {
        return dealer.canHit();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
