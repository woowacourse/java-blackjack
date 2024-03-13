package domain.dto;

import domain.card.Card;
import domain.participant.Name;

import java.util.List;

public record ParticipantDto(Name name, List<Card> cards, int score) {
}
