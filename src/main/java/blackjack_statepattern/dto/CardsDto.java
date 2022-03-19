package blackjack_statepattern.dto;

import blackjack_statepattern.card.Card;
import blackjack_statepattern.participant.Participant;
import java.util.List;
import java.util.Map;

public class CardsDto {
    private final Map<Participant, List<Card>> participantCards;

    private CardsDto(Map<Participant, List<Card>> participantCards) {
        this.participantCards = participantCards;
    }

    public static CardsDto of(Map<Participant, List<Card>> participantCards) {
        return new CardsDto(participantCards);
    }

    public Map<Participant, List<Card>> getParticipantCards() {
        return participantCards;
    }
}
