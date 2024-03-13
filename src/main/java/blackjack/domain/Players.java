package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;
import blackjack.dto.PlayerInfo;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Players {

    private static final int BLACKJACK_INIT_CARD_AMOUNT = 2;
    private static final String NAME_DUPLICATED_EXCEPTION = "플레이어의 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players of(final List<PlayerInfo> playerInfos) {
        validate(playerInfos);

        List<Player> players = playerInfos.stream()
                .map(Player::new)
                .toList();

        return new Players(players);
    }

    private static void validate(final List<PlayerInfo> playerInfos) {
        if (isDuplicated(playerInfos)) {
            throw new IllegalArgumentException(NAME_DUPLICATED_EXCEPTION);
        }
    }

    private static boolean isDuplicated(final List<PlayerInfo> playerInfos) {
        Set<String> names = playerInfos.stream()
                .map(PlayerInfo::name)
                .collect(Collectors.toSet());

        return playerInfos.size() != names.size();
    }

    public void initialDeal(final Supplier<Card> supplier) {
        IntStream.range(0, BLACKJACK_INIT_CARD_AMOUNT)
                .forEach(i -> players
                        .forEach(player -> player.draw(supplier.get())));
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
