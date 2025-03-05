package blackjack.dto;

import blackjack.domain.Participant;
import java.util.List;

public record FinalResultDto(String name, List<String> cardInfos, int score) {
    public static FinalResultDto from(Participant participant) {
        String name = participant.getName().trim();
        List<String> cardInfos = participant.getCardDeck().stream()
                .map(card -> card.getRank().getName() + card.getSuit().getName())
                .toList();
        int score = participant.calculateTotalCardScore();

        return new FinalResultDto(name, cardInfos, score);
    }
}
