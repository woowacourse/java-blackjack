package blackjack.dto;

import java.util.List;

public record ParticipantDto(String name, List<String> cardNames, int point) {
}
