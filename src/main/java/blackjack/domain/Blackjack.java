package blackjack.domain;

import blackjack.controller.BlackjackController;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Blackjack {
    private static final int INITIAL_CARD_NUMBER = 2;

    private final Dealer dealer;
    private Players players;
    private Player player;

    public Blackjack(List<String> playerNames) {
        this.dealer = new Dealer();
        this.players = new Players(playerNames);
        this.player = players.firstPlayer();
    }

    private void distributeCard(NumberGenerator numberGenerator, List<Player> players) {
        for (Player player : players) {
            player.addCard(dealer.handOutCard(numberGenerator));
        }
    }

    private void updateTurnPlayer() {
        player = players.nextPlayer(player);
    }

    public boolean cycleIsOver() {
        return Objects.isNull(player);
    }

    public Player turnPlayer() {
        if (cycleIsOver()) {
            player = players.firstPlayer();
        }
        return player;
    }

    public void addtionalCardToTurnPlayer(NumberGenerator numberGenerator, boolean addCondition) {
        if (!addCondition) {
            updateTurnPlayer();
            return;
        }
        player.addCard(dealer.handOutCard(numberGenerator));

        if (player.isBurst()) {
            updateTurnPlayer();
        }
    }

    public Players getPlayers() {
        return players;
    }

    public void distributeInitialCards(NumberGenerator numberGenerator) {
        List<Player> playersToGetAdditionalCard = players.playersAbleToGetAdditionalCard();
        playersToGetAdditionalCard.add(dealer);
        for (int i = 0; i < INITIAL_CARD_NUMBER; ++i) {
            distributeCard(numberGenerator, playersToGetAdditionalCard);
        }
    }

    public int distributeCardToDealerUntilHit(NumberGenerator numberGenerator) {
        int count = 0;
        while (dealer.isHit()) {
            distributeCardToDealer(numberGenerator);
            ++count;
        }
        return count;
    }

    private void distributeCardToDealer(NumberGenerator numberGenerator) {
        dealer.addCard(dealer.handOutCard(numberGenerator));
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Map<String, Result> results(List<Player> players) {
        return zipToMap(players.stream()
                        .map(Player::getName)
                        .collect(Collectors.toList())
                , players.stream()
                        .map(dealer::isWin)
                        .collect(Collectors.toList()));
    }

    private <K, V> Map<K, V> zipToMap(List<K> keys, List<V> values) {
        return IntStream.range(0, keys.size()).boxed()
                .collect(Collectors.toMap(keys::get, values::get));
    }
}
