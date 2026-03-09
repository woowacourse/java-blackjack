package dto;

import domain.card.Card;
import domain.participant.Participant;

import java.util.List;

public record HandDTO(String name, List<String> handCards) {

    public static HandDTO from(Participant participant) {
        return new HandDTO(
                participant.getName(), participant.getHandCards().stream()
                .map(Card::toString)
                .toList());
    }
}
