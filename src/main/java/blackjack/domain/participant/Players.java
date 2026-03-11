package blackjack.domain.participant;

import blackjack.domain.money.Money;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record Players(List<Player> players) {

    private static final String EMPTY_PLAYERS_MESSAGE = "플레이어는 1명 이상이어야 합니다.";
    private static final String DUPLICATE_NAME_MESSAGE = "중복된 이름의 플레이어가 있습니다.";

    public Players(final List<Player> players) {
        validate(players);
        this.players = List.copyOf(players);
    }

    public Map<Player, Money> placeWagers(WagerReader wagerReader) {
        Map<Player, Money> wagers = new LinkedHashMap<>();
        players
                .forEach(player -> {
                    Money wager = wagerReader.wagerOf(player);
                    wagers.put(player, wager);
                });
        return wagers;
    }

    private void validate(final List<Player> players) {
        validateNotEmpty(players);
        validateNoDuplicateNames(players);
    }

    private void validateNotEmpty(final List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_PLAYERS_MESSAGE);
        }
    }

    private void validateNoDuplicateNames(final List<Player> players) {
        final long uniqueCount = players.stream()
                .map(Player::getName)
                .distinct()
                .count();
        if (uniqueCount != players.size()) {
            throw new IllegalArgumentException(DUPLICATE_NAME_MESSAGE);
        }
    }
}
