package blackjack.dto;

import blackjack.domain.participants.GameParticipant;
import blackjack.domain.participants.Hands;
import blackjack.view.mapper.RankMapper;
import blackjack.view.mapper.ShapeMapper;
import java.util.List;

public record ParticipantDto(String playerName, List<String> allHands, int score) {

    public ParticipantDto(GameParticipant participant) {
        this(participant.getName(), handsToNames(participant.getHands()), participant.calculateScore());
    }

    private static List<String> handsToNames(Hands hands) {
        return hands.getCards().stream()
                .map(tempHands -> RankMapper.findByRank(tempHands.getRank()) + ShapeMapper.findByShape(tempHands.getShape()))
                .toList();
    }
}
