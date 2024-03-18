package blackjack.dto;

import blackjack.domain.participant.Participant;

import java.util.LinkedHashMap;
import java.util.Map;

public record ParticipantSettlementDto(Map<String, Integer> settlement) {

    public static ParticipantSettlementDto of(Map<Participant, Integer> settlement) {
        Map<String, Integer> participantSettlement = new LinkedHashMap<>();

        for (Map.Entry<Participant, Integer> result : settlement.entrySet()) {
            String name = result.getKey().getName();
            Integer settleAmount = result.getValue();
            participantSettlement.put(name, settleAmount);
        }

        return new ParticipantSettlementDto(participantSettlement);
    }
}
