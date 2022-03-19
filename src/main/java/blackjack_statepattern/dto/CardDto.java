package blackjack_statepattern.dto;

import blackjack_statepattern.card.Card;
import blackjack_statepattern.participant.Participant;
import java.util.List;
import java.util.Map;

public class CardDto {
    private final Map<Participant, List<Card>> participantCards;

    private CardDto(Map<Participant, List<Card>> participantCards) {
        this.participantCards = participantCards;
    }

    public static CardDto of(Map<Participant, List<Card>> participantCards) {
        return new CardDto(participantCards);
    }

    public Map<Participant, List<Card>> getParticipantCards() {
        return participantCards;
    }
}
