package dto;

import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;

public record ParticipantResponse(String dealerName, List<String> playerNames) {

    public static ParticipantResponse of(Dealer dealer, List<Player> players) {
        String dealerName = dealer.getNickname();
        List<String> playerNames = players.stream().map(Player::getNickname).toList();

        return new ParticipantResponse(dealerName, playerNames);
    }
}
