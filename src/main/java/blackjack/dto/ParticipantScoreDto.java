package blackjack.dto;

import blackjack.domain.game.Score;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import java.util.List;

public record ParticipantScoreDto(
    String participantName,
    List<CardNameDto> cards,
    Score score
) {
    public static ParticipantScoreDto from(Dealer dealer, Score score) {
        List<CardNameDto> cards = dealer.getCards().stream()
            .map(CardNameDto::from)
            .toList();
        return new ParticipantScoreDto(dealer.getName(), cards, score);
    }

    public static ParticipantScoreDto from(Player player, Score score) {
        List<CardNameDto> cards = player.getCards().stream()
            .map(CardNameDto::from)
            .toList();
        return new ParticipantScoreDto(player.getName(), cards, score);
    }
}
