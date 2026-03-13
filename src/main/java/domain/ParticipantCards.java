package domain;

import domain.card.Card;
import domain.participant.Name;
import java.util.List;

public record ParticipantCards(Name participantName, List<Card> cards) {
}
