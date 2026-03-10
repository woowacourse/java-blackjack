package dto;

import domain.card.Card;
import domain.state.State;
import java.util.List;

public record PlayerCardsDto(String name, List<String> cards, Integer totalScore) {
    public static PlayerCardsDto fromState(State state) {
        return new PlayerCardsDto(state.getParticipantName(), cardToString(state.getCards()), state.getScore());
    }

    private static List<String> cardToString(List<Card> hands) {
        return hands.stream()
                .map(card -> card.rank().getDisplayName() + card.suit().getDisplayName())
                .toList();

    }
}
