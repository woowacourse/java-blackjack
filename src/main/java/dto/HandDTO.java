package dto;

import domain.participant.Participant;
import util.CardMapper;

import java.util.List;

public record HandDTO(String name, List<String> handCards) {

    public static HandDTO from(Participant participant) {
        return new HandDTO(
                participant.getName(), participant.getHandCards().stream()
                .map(CardMapper::cardToKorean)
                .toList());
    }
}
