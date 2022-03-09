package blackjack.domain.player;

import java.util.List;

public class Players {

    private final Dealer dealer;
    private final List<Participant> participants;

    public Players(final List<Participant> participants, final Dealer dealer) {
        validateParticipants(participants);
        this.participants = participants;
        this.dealer = dealer;
    }

    private void validateParticipants(final List<Participant> participants) {
        if (participants == null || participants.size() < 2 || participants.size() > 8){
            throw new IllegalArgumentException("[ERROR] 참가자 정보가 잘못 입력되었습니다.");
        }
    }
}
