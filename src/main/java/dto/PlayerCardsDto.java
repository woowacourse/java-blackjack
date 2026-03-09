package dto;

import domain.card.Card;
import domain.participants.Participant;
import java.util.List;

public record PlayerCardsDto(String name, List<String> cards, Integer totalScore) {
    public static PlayerCardsDto fromEntity(Participant participant) {
        return new PlayerCardsDto(participant.getName(), cardToString(participant.getCards()),
                participant.getScore().getValue());
    }

    private static List<String> cardToString(List<Card> hands) {
        return hands.stream()
                .map(card -> card.rank().getDisplayName() + card.suit().getDisplayName())
                .toList();

    }
}
