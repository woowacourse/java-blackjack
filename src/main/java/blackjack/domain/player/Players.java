package blackjack.domain.player;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {

    private final Dealer dealer;
    private final List<Participant> participants;

    public Players(final List<Participant> participants, final Dealer dealer) {
        validateParticipants(participants);
        this.participants = participants;
        this.dealer = dealer;
    }

    private void validateParticipants(final List<Participant> participants) {
        validateSize(participants);
        validateDuplicated(participants);
    }

    private void validateSize(final List<Participant> participants) {
        if (participants == null || participants.size() < 2 || participants.size() > 8) {
            throw new IllegalArgumentException("[ERROR] 참가자 정보가 잘못 입력되었습니다.");
        }
    }

    private void validateDuplicated(final List<Participant> participants) {
        Set<String> names = extractNames(participants);

        if (names.size() != participants.size()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름은 중복될 수 없습니다.");
        }
    }

    public Set<String> extractNames(final List<Participant> participants) {
        return participants.stream()
                .map(Participant::getName)
                .collect(Collectors.toSet());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}
