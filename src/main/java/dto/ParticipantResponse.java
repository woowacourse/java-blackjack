package dto;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.List;

public record ParticipantResponse(String dealerName, List<String> playerNames) {

    public static ParticipantResponse of(Participant dealer, List<Participant> players) {
        String dealerName = dealer.getNickname();
        List<String> playerNames = players.stream().map(Participant::getNickname).toList();

        return new ParticipantResponse(dealerName, playerNames);
    }
}
