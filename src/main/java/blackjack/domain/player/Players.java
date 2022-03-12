package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.result.Judge;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {

    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 8;

    private final List<Participant> participants;
    private final Dealer dealer;

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
        if (participants == null || participants.size() < MIN_SIZE || participants.size() > MAX_SIZE) {
            throw new IllegalArgumentException("[ERROR] 참가자 정보가 잘못 입력되었습니다.");
        }
    }

    private void validateDuplicated(final List<Participant> participants) {
        final Set<String> names = participants.stream()
                .map(Player::getName)
                .collect(Collectors.toSet());

        if (names.size() != participants.size()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름은 중복될 수 없습니다.");
        }
    }

    public void competeWithParticipants() {
        for (Participant participant : participants) {
            compete(participant);
        }
    }

    private void compete(Participant participant) {
        if (Judge.compete(dealer, participant)) {
            dealer.increaseWinCount();
            return;
        }
        participant.makeWin();
        dealer.increaseLoseCount();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Participant> getParticipants() {
        return List.copyOf(participants);
    }
}
