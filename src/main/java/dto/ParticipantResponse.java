package dto;

import domain.participant.Participant;
import domain.participant.Participants;
import java.util.List;

public record ParticipantResponse(String dealerName, List<String> playerNames) {

    public static ParticipantResponse of(Participant dealer, Participants onlyPlayers) {
        String dealerName = dealer.getNickname();
        List<Participant> originOnlyPlayers = onlyPlayers.getParticipants();
        List<String> playerNames = originOnlyPlayers.stream().map(Participant::getNickname).toList();

        return new ParticipantResponse(dealerName, playerNames);
    }

//    public static ParticipantResponse of(Dealer dealer, List<Player> players) {
//        String dealerName = dealer.getNickname();
//        List<String> playerNames = players.stream().map(Player::getNickname).toList();
//
//        return new ParticipantResponse(dealerName, playerNames);
//    }
}
