package blackjack.domain.game;

import blackjack.domain.Money;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import java.util.Map.Entry;

public class ParticipantPrizeDto {
    private final String participantName;
    private final int prizeAmount;

    private ParticipantPrizeDto(String participantName, int prizeAmount) {
        this.participantName = participantName;
        this.prizeAmount = prizeAmount;
    }

    public static ParticipantPrizeDto of(Entry<Participant, Money> participantToMoney) {
        Name participantName = participantToMoney.getKey().getName();
        Money prizeAmount = participantToMoney.getValue();
        return new ParticipantPrizeDto(participantName.getValue(), prizeAmount.getValue());
    }

    public String getParticipantName() {
        return participantName;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }
}
