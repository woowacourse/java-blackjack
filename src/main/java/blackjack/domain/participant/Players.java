package blackjack.domain.participant;

import static blackjack.domain.Result.DRAW;
import static blackjack.domain.Result.LOSE;
import static blackjack.domain.Result.WIN;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {
    public static final int MIN_PLAYER_COUNT = 2;
    public static final int MAX_PLAYER_COUNT = 8;

    private final List<Player> players;

    public Players(final List<String> names) {
        validateLength(names);
        this.players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private void validateLength(List<String> names) {
        if (MAX_PLAYER_COUNT < names.size() || names.size() < MIN_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 참가자의 수는 최소 2명에서 최대 8명이어야 합니다.");
        }
    }

    public void receiveSettingCards(List<Card> settingCards) {
        for (int cardIndex = 0, playerIndex = 0; cardIndex < settingCards.size(); cardIndex += 2, playerIndex++) {
            players.get(playerIndex).receiveCard(settingCards.get(cardIndex));
            players.get(playerIndex).receiveCard(settingCards.get(cardIndex + 1));
        }
    }

    public Map<Player, Result> decideResults(int dealerScore) {
        Map<Player, Result> results = new HashMap<>();

        for (Player player : players) {
            compareScore(results, dealerScore, player);
        }

        return results;
    }

    private void compareScore(final Map<Player, Result> results, final int dealerScore, final Player player) {
        if (dealerScore > 21 && player.isBust()) {
            results.put(player, DRAW);
            return;
        }
        if (dealerScore > 21 && !player.isBust()) {
            results.put(player, WIN);
            return;
        }
        if (dealerScore <= 21 && player.isBust()) {
            results.put(player, LOSE);
            return;
        }
        if (dealerScore < player.calculateTotalScore()) {
            results.put(player, WIN);
        }
        if (dealerScore > player.calculateTotalScore()) {
            results.put(player, LOSE);
        }
        if (dealerScore == player.calculateTotalScore()) {
            results.put(player, DRAW);
        }
    }

    public int size() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
