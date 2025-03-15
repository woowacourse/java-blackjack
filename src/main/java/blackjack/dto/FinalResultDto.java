package blackjack.dto;

import blackjack.domain.Participant;
import blackjack.domain.Players;
import java.util.List;

public record FinalResultDto(
        String name,
        List<String> cardInfos,
        int score,
        boolean isBust) {

    public static FinalResultDto from(final Participant participant) {
        String name = participant.getName().trim();
        List<String> cardInfos = participant.getCardDeck().stream()
                .map(card -> card.getRank().getName() + card.getSuit().getName())
                .toList();
        int score = participant.calculateTotalCardScore();
        boolean isBust = participant.isBust();

        return new FinalResultDto(name, cardInfos, score, isBust);
    }

    public static List<FinalResultDto> fromPlayers(final Players players) {
        return players.members().stream().map(FinalResultDto::from).toList();
    }
}
