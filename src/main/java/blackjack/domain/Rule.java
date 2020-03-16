package blackjack.domain;

import blackjack.domain.participants.Participants;

public interface Rule {
    void judgeBasic(Participants participants);
}
