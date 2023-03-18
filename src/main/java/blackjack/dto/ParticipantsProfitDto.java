package blackjack.dto;

import blackjack.domain.person.Person;

import java.util.HashMap;
import java.util.Map;

public class ParticipantsProfitDto {

    private final Map<String, Double> participantsProfit;

    private ParticipantsProfitDto(Map<String, Double> participantsProfit) {
        this.participantsProfit = participantsProfit;
    }

    public static ParticipantsProfitDto of(Map<Person, Double> participantsProfit) {
        Map<String, Double> converted = convertToMapString(participantsProfit);
        return new ParticipantsProfitDto(converted);
    }

    private static Map<String, Double> convertToMapString(Map<Person, Double> participantsProfit) {
        Map<String, Double> converted = new HashMap<>();
        participantsProfit.forEach((person, profit) -> {
            String name = person.getName();
            converted.put(name, profit);
        });
        return converted;
    }

    public Map<String, Double> getParticipantsProfit() {
        return participantsProfit;
    }
}
