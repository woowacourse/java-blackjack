package blackjack.domain.participant;

import static blackjack.domain.Result.DRAW;
import static blackjack.domain.Result.LOSE;
import static blackjack.domain.Result.WIN;
import static blackjack.domain.participant.Dealer.BLACKJACK_SCORE;

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

    private void validateLength(final List<String> names) {
        if (MAX_PLAYER_COUNT < names.size() || names.size() < MIN_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 참가자의 수는 최소 2명에서 최대 8명이어야 합니다.");
        }
    }

    public void receiveSettingCards(final List<Card> settingCards) {
        for (int cardIndex = 0, playerIndex = 0; cardIndex < settingCards.size(); cardIndex += 2, playerIndex++) {
            players.get(playerIndex).receiveCard(settingCards.get(cardIndex));
            players.get(playerIndex).receiveCard(settingCards.get(cardIndex + 1));
        }
    }

    public Map<Player, Result> decideResults(final int dealerScore) {
        Map<Player, Result> results = new HashMap<>();

        for (Player player : players) {
            judge(results, dealerScore, player);
        }
        return results;
    }

    private void judge(final Map<Player, Result> results, final int dealerScore, final Player player) {
        if (dealerScore > BLACKJACK_SCORE && player.isBust()) {
            results.put(player, DRAW);
        }
        if (dealerScore > BLACKJACK_SCORE) {
            results.put(player, WIN);
        }
        if (dealerScore <= BLACKJACK_SCORE && player.isBust()) {
            results.put(player, LOSE);
        }
        results.put(player, Result.from(player.calculateTotalScore(), dealerScore));
    }

    public int size() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
