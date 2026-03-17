package domain.participant;

import static domain.participant.Dealer.DEALER_NAME;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Participants {

    public static final int MINIMUM_BOUND = 1;
    public static final int MAXIMUM_BOUND = 5;

    private static final String PLAYER_NAME_CONFLICTS_WITH_DEALER_NAME =
            "플레이어 이름은 딜리 이름인 " + DEALER_NAME + "와 달라야 합니다.";
    private static final String DUPLICATE_PLAYER_NAME = "플레이어 이름은 중복될 수 없습니다.";
    private static final String PLAYER_COUNT_OUT_OF_RANGE =
            "플레이어는 " + MINIMUM_BOUND + "명 이상, " + MAXIMUM_BOUND + "명 이하여야 합니다.";

    private final Dealer dealer;
    private final List<Player> players;

    // TODO: 이름 중복 및 딜러 이름과 중복되는지 검증 테스트 추가
    public Participants(final List<Player> players) {
        validatePlayerCounts(players);
        validateConflictsWithDealerName(players);
        validateDuplicateNameExist(players);

        this.players = new ArrayList<>(players);
        this.dealer = new Dealer();
    }


    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }


    private void validatePlayerCounts(final List<Player> players) {
        if (players.size() < MINIMUM_BOUND || players.size() > MAXIMUM_BOUND) {
            throw new IllegalArgumentException(PLAYER_COUNT_OUT_OF_RANGE);
        }
    }

    private void validateConflictsWithDealerName(final List<Player> players) {
        for (final Player player : players) {
            if (DEALER_NAME.equals(player.getName())) {
                throw new IllegalArgumentException(PLAYER_NAME_CONFLICTS_WITH_DEALER_NAME);
            }
        }
    }

    private void validateDuplicateNameExist(final List<Player> players) {
        final Set<String> names = new HashSet<>();

        for (final Player player : players) {
            names.add(player.getName());
        }

        if (names.size() != players.size()) {
            throw new IllegalArgumentException(DUPLICATE_PLAYER_NAME);
        }
    }
}
