package dto;

import domain.card.vo.Card;
import domain.participants.Participant;
import java.util.List;

public record PlayerCardsDto(String name, List<String> cards, Integer totalScore) {
    public static PlayerCardsDto fromParticipant(Participant participant) {
        return new PlayerCardsDto(participant.getName(), cardToString(participant.getState().getCards()),
                participant.getState().getScore());
    }

    private static List<String> cardToString(List<Card> hands) {
        return hands.stream()
                .map(card -> card.rank().getDisplayName() + card.suit().getDisplayName())
                .toList();

    }
}
