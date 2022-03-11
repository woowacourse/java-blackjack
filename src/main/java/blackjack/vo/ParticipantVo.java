package blackjack.vo;

import java.util.Set;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;

public class ParticipantVo {

    private final Participant participant;

    public ParticipantVo(Participant participant) {
        this.participant = participant;
    }

    public String getName() {
        return participant.getName();
    }

    public Set<Card> getCards() {
        return participant.getCards().getValue();
    }

    public int getScore() {
        return participant.getScore();
    }
}
