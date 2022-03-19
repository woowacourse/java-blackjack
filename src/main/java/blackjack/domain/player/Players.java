package blackjack.domain.player;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Players {

    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 8;

    private final List<Player> participants;
    private final Player dealer;

    public Players(final List<Player> participants, final Player dealer) {
        validateParticipants(participants);
        this.participants = participants;
        this.dealer = dealer;
    }

    private void validateParticipants(final List<Player> participants) {
        validateSize(participants);
        validateDuplicated(participants);
    }

    private void validateSize(final List<Player> participants) {
        if (participants == null || participants.size() < MIN_SIZE || participants.size() > MAX_SIZE) {
            throw new IllegalArgumentException("[ERROR] 참가자 정보가 잘못 입력되었습니다.");
        }
    }

    private void validateDuplicated(final List<Player> participants) {
        final Set<String> names = participants.stream()
                .map(Player::getName)
                .collect(Collectors.toSet());

        if (names.size() != participants.size()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름은 중복될 수 없습니다.");
        }
    }

    public Player getDealer() {
        return dealer;
    }

    public List<Player> getParticipants() {
        return List.copyOf(participants);
    }

    public void additionalParticipantsDraw(Consumer<Player> consumer) {
        for (Player participant : participants) {
            consumer.accept(participant);
        }
    }
}
