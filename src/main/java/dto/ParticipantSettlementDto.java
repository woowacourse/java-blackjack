package dto;

import domain.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public record ParticipantSettlementDto(Map<String, Integer> result) {

    public static ParticipantSettlementDto of(String dealerName, int dalerSettlement, Map<Player, Integer> playerSettlement) {
        Map<String, Integer> participantSettlement = new LinkedHashMap<>();
        participantSettlement.put(dealerName, dalerSettlement);

        for (Map.Entry<Player, Integer> result : playerSettlement.entrySet()) {
            String name = result.getKey().getName();
            Integer settlement = result.getValue();
            participantSettlement.put(name, settlement);
        }

        return new ParticipantSettlementDto(participantSettlement);
    }
}
