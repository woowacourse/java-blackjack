package blackjack.domain.participant;

import java.util.List;

public class Participants {

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        this.participants = participants;
    }


    public Dealer extractDealer() {
        return (Dealer) participants.stream()
                .filter(participant -> participant instanceof Dealer)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Dealer가 존재하지 않습니다!"));
    }
}
