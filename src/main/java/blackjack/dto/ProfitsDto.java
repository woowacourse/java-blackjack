package blackjack.dto;

import blackjack.domain.money.Money;
import blackjack.domain.participant.Participant;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public record ProfitsDto(Map<String, Integer> profits) {

    public static ProfitsDto from(Map<Participant, Money> profits) {
        return new ProfitsDto(profits.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey().getName(),
                        e -> e.getValue().value(),
                        (a, b) -> a,
                        LinkedHashMap::new)));
    }
}
