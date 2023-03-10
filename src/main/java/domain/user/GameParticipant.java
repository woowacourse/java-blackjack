package domain.user;

import domain.game.Deck;
import domain.game.GameResult;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameParticipant {

    private final List<Player> players;
    private final Dealer dealer;

    public GameParticipant(final List<String> playerNames) {
        this.players = makePlayersWithName(playerNames);
        this.dealer = makeDealer();
    }

    private List<Player> makePlayersWithName(final List<String> playerNames) {
        return playerNames.stream()
                .map(playerName -> new Player(playerName, new CardPool(Collections.emptyList()), 0))
                .collect(Collectors.toList());
    }

    private Dealer makeDealer() {
        return new Dealer(new CardPool(Collections.emptyList()));
    }

    public void letPlayersToHit(final Deck deck) {
        for (Player player : players) {
            player.draw(deck.serve());
        }
        dealer.draw(deck.serve());
    }

    public void letDealerHitUntilThreshold(final Deck deck) {
        while (dealer.needsHit()) {
            dealer.draw(deck.serve());
        }
    }

    public boolean dealerNeedsHit() {
        return dealer.needsHit();
    }

    public Map<Player, GameResult> makeGameResultForAllPlayer() {
        Map<Player, GameResult> record = new HashMap<>();
        recordGameResult(record);

        return record;
    }

    private void recordGameResult(final Map<Player, GameResult> record) {
        players.forEach(player -> record.put(player, GameResult.makePlayerRecord(player, dealer)));
    }

    public void updateBetAmountByGameResult() {
        Map<Player, GameResult> record = makeGameResultForAllPlayer();

        // Player가 이기면, 기본 1배, 블랙잭 시 1.5배의 수익을 얻는다
        // 딜러의 수익은 -(플레이어의 수익)이 된다

        record.entrySet().stream()
                .filter(entry -> entry.getValue() == GameResult.WIN)
                .map(Map.Entry::getKey)
                .forEach(Player::increaseRevenue);

        record.entrySet().stream()
                .filter(entry -> entry.getValue() == GameResult.LOSE)
                .map(Map.Entry::getKey)
                .forEach(Player::decreaseRevenue);

        record.keySet().forEach(dealer::payFor);
    }

    public Map<GameResult, Integer> getDealerRecord(final Map<Player, GameResult> record) {
        return GameResult.makeDealerRecord(record);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
