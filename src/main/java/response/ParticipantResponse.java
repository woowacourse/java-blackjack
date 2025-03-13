package response;

import java.util.List;
import object.participant.Participant;

public record ParticipantResponse(String dealerName, List<String> playerNames) {

    public static ParticipantResponse of(Participant dealer, List<Participant> players) {
        String dealerName = dealer.getNickname();
        List<String> playerNames = players.stream().map(Participant::getNickname).toList();

        return new ParticipantResponse(dealerName, playerNames);
    }
}
