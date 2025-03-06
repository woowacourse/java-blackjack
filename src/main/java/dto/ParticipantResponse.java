package dto;

import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;

public record ParticipantResponse(String dealer, List<String> playerNames) {

    public static ParticipantResponse of(Dealer dealer, List<Player> players) {
        String dealerName = dealer.getName();
        List<String> playerNames = players.stream().map(Player::getName).toList();

        return new ParticipantResponse(dealerName, playerNames);
    }
}
