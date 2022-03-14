package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.HashSet;
import java.util.List;

public class Players {

    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 8;

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
        if (participants == null || participants.size() < MIN_SIZE || participants.size() > MAX_SIZE) {
            throw new IllegalArgumentException("[ERROR] 참가자의 수는 2~8명 입니다.");
        }
    }

    private void validateDuplicated(final List<Participant> participants) {
        final HashSet<Participant> notDuplicatedParticipants = new HashSet<>(participants);

        if (notDuplicatedParticipants.size() != participants.size()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름은 중복될 수 없습니다.");
        }
    }

    public void addDealerCard(final Card card) {
        this.dealer.addCard(card);
    }

    public void competeWithDealer() {
<<<<<<< HEAD
        participants.forEach(player ->
                dealer.compete(player));
=======
        for (Participant participant : participants) {
            dealer.compete(participant);
        }
>>>>>>> step1
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}
