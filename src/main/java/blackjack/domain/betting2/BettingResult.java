package blackjack.domain.betting2;

import blackjack.domain.participant2.Participant;
import blackjack.dto2.ParticipantCardsDto;

public class BettingResult {

    private final ParticipantCardsDto participantCards;
    private final int profit;

    public BettingResult(Participant participant, int profit) {
        this.participantCards = ParticipantCardsDto.of(participant);
        this.profit = profit;
    }

    public String getParticipantName() {
        return participantCards.getName();
    }

    public ParticipantCardsDto getParticipantCardsDto() {
        return participantCards;
    }

    public int getProfit() {
        return profit;
    }

    @Override
    public String toString() {
        return "BettingResult{" +
                "participantCards=" + participantCards +
                ", moneyOutcome=" + profit +
                '}';
    }
}
