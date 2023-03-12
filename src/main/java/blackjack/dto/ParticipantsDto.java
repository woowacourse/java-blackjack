package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

import java.util.HashMap;
import java.util.Map;

public class ParticipantsDto {
    private final CardsDto dealerCards;
    private final Map<String, CardsDto> participantsMap;

    public ParticipantsDto(Participants participants) {
        participantsMap = new HashMap<>();
        Dealer dealer = participants.getDealer();
        this.dealerCards = new CardsDto(dealer.getCards(), dealer.getActualScore());
        for (Player player : participants.getPlayers()) {
            putParticipant(player);
        }
    }

    public Map<String, CardsDto> getParticipantsMap() {
        return participantsMap;
    }

    private void putParticipant(Participant participant) {
        participantsMap.put(participant.getName(), new CardsDto(participant.getCards(), participant.getActualScore()));
    }

    public CardsDto getDealerCards() {
        return dealerCards;
    }
}
