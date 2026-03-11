package blackjack.domain;

import java.util.List;

public record ParticipantResult(String name, List<String> cardNames, int score) {
}
