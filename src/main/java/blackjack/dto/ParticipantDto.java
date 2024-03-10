package blackjack.dto;

import blackjack.domain.deck.Hands;
import blackjack.domain.participants.GameParticipant;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import java.util.List;

public record ParticipantDto(String playerName, List<String> allHands, int score) {
    public static ParticipantDto from(GameParticipant participant) {
        Name name = participant.getName();
        Hands hands = participant.getHands();
        List<String> allHands = hands.getCards().stream()
                .map(tempHands -> tempHands.getRank().name() + tempHands.getShape().name())
                .toList();
        return new ParticipantDto(name.getName(), allHands, participant.calculateScore());
    }
}
