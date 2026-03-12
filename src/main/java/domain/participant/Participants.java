package domain.participant;

import static exception.ErrorMessage.PLAYER_COUNT_OUT_OF_RANGE;

import domain.card.Hand;
import java.util.ArrayList;
import java.util.List;

public class Participants {

    public static final int MINIMUM_BOUND = 1;
    public static final int MAXIMUM_BOUND = 5;

    public static final String DEALER_NAME = "딜러";

    private final Participant dealer;
    private final List<Participant> players;

    public Participants(final List<Participant> players) {
        validatePlayerCounts(players);
        // TODO: 플레이어 이름 중복 검증 및 딜러 이름과 같은지 검증

        dealer = new Participant(new Name(DEALER_NAME), new Hand());
        this.players = new ArrayList<>(players);
    }


    public Participant getDealer() {
        return dealer;
    }

    public List<Participant> getPlayers() {
        return List.copyOf(players);
    }


    // TODO: 검증에 대한 테스트 필요
    private static void validatePlayerCounts(final List<Participant> participants) {
        if (participants.size() < MINIMUM_BOUND || participants.size() > MAXIMUM_BOUND) {
            throw new IllegalStateException(PLAYER_COUNT_OUT_OF_RANGE.getMessage());
        }
    }
}
