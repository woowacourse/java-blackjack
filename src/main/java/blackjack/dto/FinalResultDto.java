package blackjack.dto;

import blackjack.domain.Score;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
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
        Score score = participant.getScore();
        boolean isBust = participant.getState().isBust();

        return new FinalResultDto(name, cardInfos, score.intValue(), isBust);
    }

    public static List<FinalResultDto> fromPlayers(final List<Player> scores) {
        return scores.stream().map(FinalResultDto::from).toList();
    }
}
