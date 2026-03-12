package domain.participant;

import static exception.ErrorMessage.PLAYER_COUNT_OUT_OF_RANGE;

import java.util.ArrayList;
import java.util.List;

public class Participants {

    public static final int MINIMUM_BOUND = 1;
    public static final int MAXIMUM_BOUND = 5;

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(final List<Player> players) {
        validatePlayerCounts(players);
        // TODO: 플레이어 이름 중복 검증 및 딜러 이름과 같은지 검증 추가

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
            throw new IllegalArgumentException(PLAYER_COUNT_OUT_OF_RANGE.getMessage());
        }
    }
}
