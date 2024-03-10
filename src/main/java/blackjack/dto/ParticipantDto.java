package blackjack.dto;

import blackjack.domain.Hands;
import blackjack.domain.participants.GameParticipant;
import blackjack.domain.participants.Name;
import blackjack.view.mapper.RankMapper;
import blackjack.view.mapper.ShapeMapper;
import java.util.List;

public record ParticipantDto(String playerName, List<String> allHands, int score) {
    public static ParticipantDto from(GameParticipant participant) {
        Name name = participant.getName();
        Hands hands = participant.getHands();
        List<String> allHands = hands.getCards().stream()
                .map(tempHands -> RankMapper.findByRank(tempHands.getRank()).getRankName() + ShapeMapper.findByShape(tempHands.getShape()).getShapeName())
                .toList();
        return new ParticipantDto(name.getName(), allHands, participant.calculateScore());
    }
}
