package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Participants {
    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validate(participants);
        this.participants = new ArrayList<>(participants);
    }

    public void validate(List<Participant> paritipants) {
        if (paritipants.size() <= 2 || paritipants.size() >= 8) {
            throw new IllegalArgumentException("참여자는 2~8명 이여야 합니다.");
        }
    }
}
