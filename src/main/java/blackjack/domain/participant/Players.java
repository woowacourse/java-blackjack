package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.*;

public class Players {
    private static final int MINIMUM_PLAYER_SIZE = 1;
    private static final int MAXIMUM_PLAYER_SIZE = 6;

    private final List<Player> players;

    public Players(final List<Player> players) {
        validatePlayers(players);
        this.players = new ArrayList<>(players);
    }

    private void validatePlayers(final List<Player> players) {
        validateDuplicatedName(players);
        validatePlayerSize(players);
    }

    private void validateDuplicatedName(final List<Player> players) {
        if (players.size() != new HashSet<>(players).size()) {
            throw new IllegalArgumentException("플레이어 이름 중 중복된 이름이 존재할 수 없습니다.");
        }
    }

    private void validatePlayerSize(final List<Player> players) {
        final int size = players.size();

        if (size < MINIMUM_PLAYER_SIZE || size > MAXIMUM_PLAYER_SIZE) {
            throw new IllegalArgumentException(
                    String.format("게임에 참여하는 플레이어는 %d ~ %d명이어야 합니다.", MINIMUM_PLAYER_SIZE, MAXIMUM_PLAYER_SIZE));
        }
    }

    public int count() {
        return players.size();
    }

    public Player findPlayerByIndex(final int playerIndex) {
        return players.get(playerIndex);
    }

    public void receiveCardFrom(final Dealer dealer) {
        for (final Player player : players) {
            final Card card = dealer.pickCard();
            player.receiveCard(card);
        }
    }

    public Map<Player, ResultStatus> compareTo(final Dealer dealer) {
        final Map<Player, ResultStatus> result = new HashMap<>();

        for (Player player : players) {
            result.put(player, ResultStatus.of(player, dealer));
        }

        return Collections.unmodifiableMap(result);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
