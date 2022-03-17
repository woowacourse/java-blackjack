package blackjack.domain.betting;

import blackjack.domain.participant.Participant;
import blackjack.dto.ParticipantCardsDto;

public class BettingResult {

    private final ParticipantCardsDto participantCards;
    private final int moneyOutcome;

    public BettingResult(Participant participant, int moneyOutcome) {
        this.participantCards = ParticipantCardsDto.of(participant);
        this.moneyOutcome = moneyOutcome;
    }

    public String getParticipantName() {
        return participantCards.getName();
    }

    public ParticipantCardsDto getParticipantCardsDto() {
        return participantCards;
    }

    public int getMoneyOutcome() {
        return moneyOutcome;
    }

    @Override
    public String toString() {
        return "BettingResult{" +
                "participantCards=" + participantCards +
                ", moneyOutcome=" + moneyOutcome +
                '}';
    }
}
