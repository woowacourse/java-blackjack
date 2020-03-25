package blackjack.domain.result;

import blackjack.domain.participants.Participants;

public interface Result {
    void judge(Participants participants);
}
