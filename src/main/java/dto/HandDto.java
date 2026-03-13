package dto;

import domain.participant.Participant;
import util.CardMapper;

import java.util.List;

public record HandDto(String name, List<String> handCards) {

    public static HandDto from(Participant participant) {
        return new HandDto(
                participant.getName(), participant.getHandCards().stream()
                .map(CardMapper::cardToKorean)
                .toList());
    }
}
