package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;

import java.util.LinkedHashMap;
import java.util.Map;

public class ParticipantsDto {
    private final CardsDto dealerCards;
    private final Participants participants;

    public ParticipantsDto(Participants participants) {
        this.participants = participants;
        Dealer dealer = participants.getDealer();
        this.dealerCards = new CardsDto(dealer.getCards(), dealer.getActualScore());
    }

    public Map<String, CardsDto> getParticipantsMap() {
        Map<String, CardsDto> participantsMap = new LinkedHashMap<>();
        participants.getPlayers().forEach(player -> {
            participantsMap.put(player.getName(), new CardsDto(player.getCards(), player.getActualScore()));
            ;
        });
        return participantsMap;
    }

    public CardsDto getDealerCards() {
        return dealerCards;
    }

    public int getDealerRevenue() {
        return this.participants.getDealerRevenue();
    }

    public Map<String, Integer> getPlayersRevenue() {
        Map<String, Integer> playersRevenue = new LinkedHashMap<>();
        participants.getPlayers().forEach(player -> {
            playersRevenue.put(player.getName(), player.getRevenue());
        });
        return playersRevenue;
    }
}
